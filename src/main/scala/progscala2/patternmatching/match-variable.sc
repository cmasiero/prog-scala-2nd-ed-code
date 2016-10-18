// src/main/scala/progscala2/patternmatching/match-variable.sc

/**
  * Cristiano:Pattern Matching. Values, Variables, and Types in Matches
  * Matches, like all expressions, return a value.
  * In this case, all clauses return strings, so the return type
  * of the whole clause is String.
  * The compiler infers the closest supertype
  * (also called the least upper bound) for types of values returned
  * by all the case clauses.
  *
  * Matches are eager, so more specific clauses must appear
  * before less specific clauses.
  *
  */
for {
  x <- Seq(1, 2, 2.7, "one", "two", 'four)                           // <1>
} {
  val str = x match {                                                // <2>
    case 1          => "int 1"                                       // <3>
    case i: Int     => "other int: "+i                               // <4>
    case d: Double  => "a double: "+x                                // <5>
    case "one"      => "string one"                                  // <6>
    case s: String  => "other string: "+s                            // <7>
    case unexpected => "unexpected value: " + unexpected             // <8>
  }
  println(str)                                                       // <9>
}

