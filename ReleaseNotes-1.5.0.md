## Release Notes 1.5.0-Snapshot

### Breaking changes & bug fixes
+ Moved runnable from pimpathon.java.lang to pimpathon package

### Removals

### Additions
+ [A: Numeric].bounded(A, A): A
+ Boolean.implies(Boolean): Boolean
+ Boolean.nor(Boolean): Boolean
+ Option[E].toFailureNel(A): ValidationNel[E, A]
+ GTL[A].apoFold(B)((B, A) ⇒ Either[C, B]): Either[C, B]
+ List[A].toNel: Option[NonEmptyList[A]]
+ Callable[A].attempt: Callable[Try[A]]
+ File.readString()(implicit Codec): String
+ File.source()(implicit Codec): BufferedSource  -- Added implicit Codec argument
+ File.readLines()(implicit Codec): List[String] -- Added implicit Codec argument
+ Throwable.stackTraceAsString: String