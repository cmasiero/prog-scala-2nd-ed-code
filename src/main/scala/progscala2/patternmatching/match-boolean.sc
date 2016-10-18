// src/main/scala/progscala2/patternmatching/match-boolean.sc

/**
  * Cristiano:Pattern Matching, Simple Match
  *
  *
  *
  */
val bools = Seq(true, false)

for (bool <- bools) {
  bool match {
    case true => println("Got heads")
    case false => println("Got tails")
  }
}

for (bool <- bools) {
  val which = if (bool) "head" else "tails"
  println("Got " + which)
}
