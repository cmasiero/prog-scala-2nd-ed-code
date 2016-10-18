// src/main/scala/progscala2/patternmatching/match-tuple.sc

/**
  * Cristiano:Pattern Matching, Matching on Tuples
  *
  */
val langs = Seq(
  ("Scala",   "Martin", "Odersky"),
  ("Clojure", "Rich",   "Hickey"),
  ("Lisp",    "John",   "McCarthy"))

for (tuple <- langs) {
  tuple match {
    case ("Scala", _, _) => println("Found Scala")                   // <1>
    case (lang, first, last) =>                                      // <2>
      println(s"Found other language: $lang ($first, $last)")
  }
}
