package pimpathon

import _root_.java.io.{ByteArrayInputStream, ByteArrayOutputStream}
import scala.collection.mutable.ListBuffer
import scala.collection.{mutable ⇒ M}
import scala.reflect.ClassManifest
import scala.util.DynamicVariable
import scalaz.syntax.std.ToBooleanOps
import scalaz.std.ListFunctions

import org.junit.Assert._
import pimpathon.manifest._


object util extends ListFunctions {
  def assertException[E <: Throwable](expectedMessage: String)(f: ⇒ Unit)
    (implicit expected: ClassManifest[E]): Unit = assertEquals(expectedMessage, intercept[E](f).getMessage)

  def assertInputStreamClosed(expected: Boolean, closed: Boolean): Unit =
    assertEquals("expected InputStream to %s closed".format(if (expected) "be" else "not be"), expected, closed)

  def assertOutputStreamClosed(expected: Boolean, closed: Boolean): Unit =
    assertEquals("expected OutputStream to %s closed".format(if (expected) "be" else "not be"), expected, closed)

  def assertEqualsSet[A](expected: Set[A], actual: Set[A]): Unit = {
    val (missing, extra) = (expected -- actual, actual -- expected)

    assertTrue("Extra: %s, Missing: %s".format(extra, missing), extra.isEmpty && missing.isEmpty)
  }

  def createInputStream(content: String = "", onClose: () ⇒ Unit = () ⇒ {}) =
    inputStreamFor(content.getBytes, onClose)

  def inputStreamFor(content: Array[Byte], onClose: () ⇒ Unit = () ⇒ {}) = new ByteArrayInputStream(content) {
    var closed = false
    override def close() = { closed = true; super.close(); onClose() }
  }

  def createOutputStream(onClose: () ⇒ Unit = () ⇒ {}) = new ByteArrayOutputStream() {
    var closed: Boolean = false
    override def close(): Unit = { closed = true; super.close(); onClose() }
  }

  def ignoreExceptions(f: ⇒ Unit): Unit = try f catch { case t: Throwable ⇒ }

  def intercept[E <: Throwable](f: ⇒ Any)(implicit expected: ClassManifest[E]): E = {
    val clazz = expected.erasure

    val caught = try { f; None } catch {
      case u: Throwable ⇒ if (clazz.isAssignableFrom(u.getClass)) Some(u) else {
        sys.error("Invalid exception, expected %s, got: ".format(clazz.getName) + u)
      }
    }

    caught match {
      case None ⇒ sys.error("Expected exception: %s".format(clazz.getName))
      case Some(e) ⇒ e.asInstanceOf[E]
    }
  }

  def nil[A]: List[A] = Nil

  def goBoom: Nothing = throw boom
  val boom = new Throwable("Boom !")

  def currentTime(): Long = dynamicTime.value
  def withTime[A](millis: Long)(f: ⇒ A): A = dynamicTime.withValue(millis)(f)

  def partial[A, B](entries: (A, B)*): PartialFunction[A, B] = entries.toMap

  def ints(is: Int*): ListBuffer[Int] = new M.ListBuffer[Int] ++= is
  def strings(ss: String*): ListBuffer[String] = new M.ListBuffer[String] ++= ss

  private val dynamicTime = new DynamicVariable[Long](0)
}