## Release Notes 1.6.0

### Breaking changes & bug fixes

### Removals

### Additions
+ [A].isOneOf(A*): Boolean
+ [A].isNotOneOf(A*): Boolean
+ [A].containedIn(Set[A]): Boolean
+ [A].notContainedIn(Set[A]): Boolean
+ Try[A].fold(Throwable => B, A => B): Option[String]
+ Try[A].toDisjunction: Throwable \/ A
+ List[A].initOption: Option[List[A]]
+ List[A].onlyDisjunction: List[A] \/ A
+ GTL[A].asMap.withEntries(A => K, A => V): Map[K, V]
+ GTL[A].asMap.withSomeEntries(A => Option[(K, V)]): Map[K, V]
+ GTL[A].asMap.withSomeEntries(A => Option[K], A => Option[V]): Map[K, V]
+ GTL[A].asMap.withPFEntries(PartialFunction[A, (K, V)]): Map[K, V]
+ GTL[A].asMap.withPFEntries(PartialFunction[A, K], PartialFunction[A, V]): Map[K, V]
+ GTL[A].asMultiMap.withEntries(A => K, A => V): MultiMap[GTL, K, V]
+ GTL[A].asMultiMap.withSomeEntries(A => Option[(K, V)]): MultiMap[GTL, K, V]
+ GTL[A].asMultiMap.withSomeEntries(A => Option[K], A => Option[V]): MultiMap[GTL, K, V]
+ GTL[A].asMultiMap.withPFEntries(PartialFunction[A, (K, V)]): MultiMap[GTL, K, V]
+ GTL[A].asMultiMap.withPFEntries(PartialFunction[A, K] PartialFunction[A, V]): MultiMap[GTL, K, V]
+ GTL[A].all(A): Boolean
+ GTL[A].none(A): Boolean
+ GTL[L \/ R].partitionDisjunctions: (GTL[L], GTL[R])
+ file.resource(String): Option[File]
+ (L \/ R).tap(L => Discarded, R => Discarded): L \/ R
+ (L \/ R).tapLeft(L => Discarded): L \/ R
+ (L \/ R).tapRight(R => Discarded): L \/ R
+ (L \/ R).addTo(Growable[L], Growable[R]): L \/ R
+ (L \/ R).removeFrom(Shrinkable[L], Shrinkable[R]): L \/ R
+ (PartialFunction[A, B] &&& PartialFunction[A, C]): PartialFunction[A, (B, C)]
+ String.toByteArray: Array[Byte]
+ String.toByteArray(Charset): Array[Byte]
+ ThreadFactory.naming(Int => String): ThreadFactory
+ argonaut.CodecJson[A].andThen(Json => Json): CodecJson[A]
+ argonaut.CodecJson[A].compose(Json => Json): CodecJson[A]
+ argonaut.DecodeJson[A].compose(Json => Json): DecodeJson[A]
+ argonaut.DecodeJson[A].upcast[B >: A]: DecodeJson[B]
+ argonaut.EncodeJson[A].andThen(Json => Json): EncodeJson[A]
+ argonaut.EncodeJson[A].downcast[B <: A]: EncodeJson[B]