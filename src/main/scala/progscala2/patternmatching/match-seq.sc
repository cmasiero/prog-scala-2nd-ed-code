// src/main/scala/progscala2/patternmatching/match-seq.sc


/**
  * Cristiano:Pattern Matching, Matching on Sequences
  *
  * <6> The first matches on any nonempty Seq, extracting the head,
  *     the first element, and the tail, which is the rest of the Seq.
  *     (Seq has head and tail methods, but here these terms are interpreted
  *     as variable names as usual for case clauses.)
  *     The body of the clause constructs a String with the head followed
  *     by: followed by the result of calling seqToString on the tail.
  *     The operator +: is the “cons” (construction) operator for sequences.
  *     It is similar to the :: operator for Lists.
  *     Recall that methods that end with a colon (:) bind to the right,
  *     toward the Seq tail.
  *
  * <7> The Scala library has an object called Nil for lists and it matches
  *     all empty sequences, which is why we used it.
  *     We can use Nil even for collections that aren’t a List because of
  *     the way equality for sequences is implemented.
  */

val nonEmptySeq    = Seq(1, 2, 3, 4, 5)                              // <1>
val emptySeq       = Seq.empty[Int]
val nonEmptyList   = List(1, 2, 3, 4, 5)                             // <2>
val emptyList      = Nil
val nonEmptyVector = Vector(1, 2, 3, 4, 5)                           // <3>
val emptyVector    = Vector.empty[Int]
val nonEmptyMap    = Map("one" -> 1, "two" -> 2, "three" -> 3)       // <4>
val emptyMap       = Map.empty[String,Int]

def seqToString[T](seq: Seq[T]): String = seq match {                // <5>
  case head +: tail => s"$head +: " + seqToString(tail)              // <6>
  case Nil => "Nil"                                                  // <7>
}

for (seq <- Seq(                                                     // <8>
    nonEmptySeq, emptySeq, nonEmptyList, emptyList, 
    nonEmptyVector, emptyVector, nonEmptyMap.toSeq, emptyMap.toSeq)) {
  println(seqToString(seq))
}

println("nonEmptySeq.head: " + nonEmptySeq.head)
println("nonEmptySeq.tail: " + nonEmptySeq.tail)
