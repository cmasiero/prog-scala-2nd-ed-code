// src/main/scala/progscala2/patternmatching/match-seq-parens.sc


/**
  * Cristiano:Pattern Matching, Matching on Sequences
  *           The output of this script is the following,
  *           which shows the hierarchical structure explicitly,
  *           where each “sublist” is surrounded by parentheses:
  **/
val nonEmptySeq    = Seq(1, 2, 3, 4, 5)
val emptySeq       = Seq.empty[Int]
val nonEmptyMap    = Map("one" -> 1, "two" -> 2, "three" -> 3)

def seqToString2[T](seq: Seq[T]): String = seq match {
  case head +: tail => s"($head +: ${seqToString2(tail)})"           // <1>
  case Nil => "(Nil)"
}

for (seq <- Seq(nonEmptySeq, emptySeq, nonEmptyMap.toSeq)) {
  println(seqToString2(seq))
}
