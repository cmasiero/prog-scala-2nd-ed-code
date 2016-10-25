// src/main/scala/progscala2/fp/datastructs/seq.sc

/**
  * Cristiano:Cristiano:Functional Data Structures - Seq
  * For Seq, the “cons” operator is  : instead of ::.
  * Note that when you use the Seq.apply method on the
  * companion object, it constructs a List,
  * because Seq is a trait, not a concrete class:
  */

val seq1 = Seq("Programming", "Scala")                               // <1>
val seq2 = "People" +: "should" +: "read" +: seq1                    // <2>

val seq3 = "Programming" +: "Scala" +: Seq.empty                     // <3>
val seq4 = "People" +: "should" +: "read" +: Seq.empty
val seq5 = seq4 ++ seq3                                              // <4>

/**
  * You append an element with :+
  * and prepend an element with  +:.
  */
val seq10 = Seq("Programming", "Scala")
val seq11 = seq10 :+ "Rocks!"
println(seq11.toString())
