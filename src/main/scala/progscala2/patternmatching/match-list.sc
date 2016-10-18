// src/main/scala/progscala2/patternmatching/match-list.sc

/**
  * Cristiano:Pattern Matching, Matching List
  *           Before Scala 2.10, it was common to use a closely related
  *           idiom for Lists instead:
  * <1> Replaced : with ::.
  *     The output is similar: (1 :: (2 :: (3 :: (4 :: (5 :: (Nil)))))) (Nil)
  */

val nonEmptyList = List(1, 2, 3, 4, 5)
val emptyList    = Nil

def listToString[T](list: List[T]): String = list match {
  case head :: tail => s"($head :: ${listToString(tail)})"           // <1>
  case Nil => "(Nil)"
}

for (l <- List(nonEmptyList, emptyList)) { println(listToString(l)) }
