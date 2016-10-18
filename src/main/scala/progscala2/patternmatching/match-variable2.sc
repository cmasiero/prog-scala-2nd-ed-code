// src/main/scala/progscala2/patternmatching/match-variable2.sc

/**
  * Cristiano:Pattern Matching. Values, Variables, and Types in Matches
  * When the input is of type Any, end with a default match clause,
  * case _ or case some_name.
  *
  * There are a few rules and gotchas to keep in mind when writing
  * case clauses.
  * The compiler assumes that a term that starts with a capital letter
  * is a type name, while a term that begins with a lowercase letter
  * is assumed to be the name of a variable that will hold an extracted
  * or matched value.
  */

for {
  x <- Seq(1, 2, 2.7, "one", "two", 'four)
} {
  val str = x match {
    case 1          => "int 1"
    case _: Int     => "other int: "+x
    case _: Double  => "a double: "+x
    case "one"      => "string one"
    case _: String  => "other string: "+x
    case _          => "unexpected value: " + x
  }
  println(str)
}

