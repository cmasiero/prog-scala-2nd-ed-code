// src/main/scala/progscala2/fp/curry/curried-func.sc

/**
  * Cristiano:Partially Applied Functions Versus Partial Functions
  * The key phrase is partially applied function.
  * For a function with more than one argument list, you can define
  * a new function if you omit one or more of the trailing
  * argument lists (<1>).
  *
  * Note :
  * A partially applied function is an expression with some,
  * but not all of a function’s argument lists applied (or provided).
  *
  */

/**
  * A partial function is a single-argument function that is not
  * defined for all values of the type of its argument.
  * The real point is that inverse is “partial” for all Doubles except for 0.0.
  */
val inverse: PartialFunction[ Double, Double] = {
  case d if d != 0.0 => 1.0 / d
}


def cat1(s1: String)(s2: String) = s1 + s2
val hello = cat1("Hello") _
println(hello(" cristiano")) // <1>


/**
  * In Scala, curried functions are defined with multiple argument lists,
  * each with a single argument.
  */
def cat2(s1: String) = (s2: String) => s1 + s2
def cat3(s1: String, s2: String) = s1 + s2

/**
  * Cristiano:Currying and Other Transformations on Functions
  * Currying transforms a function that takes multiple arguments
  * into a chain of functions, each taking a single argument.
  */

val cat3Curried = (cat3 _).curried
// cat3Curried: String => (String => String) = <function1>

cat3Curried("hello")("world")
// helloworld

cat3("hello", "world")
// helloworld

val cat3Uncurried = Function.uncurried(cat3Curried)
// cat3Uncurried: (String, String) => String = <function2>

cat3Uncurried("hello", "world")
// helloworld