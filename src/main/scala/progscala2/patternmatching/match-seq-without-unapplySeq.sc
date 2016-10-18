// src/main/scala/progscala2/patternmatching/match-seq-without-unapplySeq.sc

/**
  * Cristiano:Matching, without unapplySeq Method
  * We could still use the +: matching we saw before, which is more elegant:
  * For unapplySeq implementation see:match-seq-unapplySeq.sc
  */

val nonEmptyList   = List(1, 2, 3, 4, 5)
val emptyList      = Nil
val nonEmptyMap    = Map("one" -> 1, "two" -> 2, "three" -> 3)

// Process pairs
def windows2[T](seq: Seq[T]): String = seq match {
  case head1 +: head2 +: tail => s"($head1, $head2), " + windows2(seq.tail)
  case head +: tail => s"($head, _), " + windows2(tail)
  case Nil => "Nil"
}

for (seq <- Seq(nonEmptyList, emptyList, nonEmptyMap.toSeq)) {
  println(windows2(seq))
}
