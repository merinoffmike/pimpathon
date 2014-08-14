package pimpathon.java.io

import java.io.{InputStream, OutputStream, ByteArrayInputStream, ByteArrayOutputStream}
import org.junit.Test

import org.junit.Assert._
import pimpathon.java.io.inputStream._
import pimpathon.util._


class InputStreamTest {
  @Test def closeIf {
    val is = createInputStream()
    assertInputStreamClosed(false, is.closed)

    is.closeIf(false)
    assertInputStreamClosed(false, is.closed)

    is.closeIf(true)
    assertInputStreamClosed(true, is.closed)
  }

  @Test def read {
    for {
      expectedCloseIn  <- List(false, true)
      expectedCloseOut <- List(false, true)
      input            <- List("Input", "Repeat" * 100)
    } {
      val (is, os) = (createInputStream(input.getBytes), createOutputStream())

      is.read(os, expectedCloseIn, expectedCloseOut)

      assertEquals(input, os.toString)
      assertInputStreamClosed(expectedCloseIn, is.closed)
      assertOutputStreamClosed(expectedCloseOut, os.closed)
    }
  }
}
