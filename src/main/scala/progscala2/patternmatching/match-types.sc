// src/main/scala/progscala2/patternmatching/match-types.sc

/**
  * Cristiano:More on Type Matching
  *
  * Note: What do the warnings mean? Scala runs on the JVM and these
  * warnings result from the JVM’s type erasure, a historical legacy
  * of Java’s introduction of generics in Java 5.
  *
  * One ugly, but effective workaround is to match on the collection first,
  * then use a nested match on the head element to determine the type.
  * We now have to handle an empty sequence, too:
  * src/main/scala/progscala2/patternmatching/match-types2.sc
  *
  */

for {
  x <- Seq(List(5.5,5.6,5.7), List("a", "b")) 
} yield (x match {
  case seqd: Seq[Double] => ("seq double", seqd)
  case seqs: Seq[String] => ("seq string", seqs)
  case _                 => ("unknown!", x)
})
