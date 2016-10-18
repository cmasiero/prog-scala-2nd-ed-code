// src/main/scala/progscala2/patternmatching/match-surprise-fix.sc

/**
  * Cristiano:Pattern Matching. Values, Variables, and Types in Matches
  *
  * Use “back ticks” to indicate we really want to match against
  * the value held by y.
  *
  *
  * Again: In case clauses, a term that begins with a lowercase letter
  * is assumed to be the name of a new variable that will hold an extracted
  * value.
  * To refer to a previously defined variable, enclose it in back ticks.
  * Conversely, a term that begins with an uppercase letter is assumed
  * to be a type name.
  *
  */

def checkY(y: Int) = {
  for {
    x <- Seq(99, 100, 101)
  } {
    val str = x match {
      case `y` => "found y!"           // The only change: `y`
      case i: Int => "int: "+i
    }
    println(str)
  }
}
checkY(100)
