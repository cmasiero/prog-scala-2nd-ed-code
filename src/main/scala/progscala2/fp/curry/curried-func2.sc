// src/main/scala/progscala2/fp/curry/curried-func2.sc

/**
  * Cristiano:Currying and Other Transformations on Functions
  * A practical use for currying is to specialize functions
  * for particular types of data.
  */

val f1: String =>  String => String  = (s1: String) => (s2: String) => s1 + s2
val f2: String => (String => String) = (s1: String) => (s2: String) => s1 + s2

f1("hello")("world")
// helloworld

f2("hello")("world")
// helloworld

val ff1 = Function.uncurried(f1)
val ff2 = Function.uncurried(f2)

ff1("hello", "world")
// helloworld

ff2("hello", "world")
// helloworld


/**
  * Cristiano:Currying and Other Transformations on Functions
  * As a simple example of this approach, the following code provides
  * specialized forms of a base function that handles multiplication:
  */
def multiplier( i: Int)( factor: Int) = i * factor
val byFive = multiplier(5) _
val byTen  = multiplier(10) _
println(byFive(10))
println(byTen(10))

/**
  * Cristiano:Currying and Other Transformations on Functions - tuple
  * One scenario you’ll encounter occasionally is when you have data in a tuple,
  * let’s say a three-element tuple, and you need to call a three-argument function:
  */
def mult(d1: Double,d2: Double,d3: Double) = d1 * d2 * d3
val d3 = (2.2, 3.3, 4.4)
mult(d3._1,d3._2,d3._3)
/**
  * We would love to have a new version of mult that takes the tuple
  * itself as a single argument.
  * Fortunately, the Function object provides tupled and untupled
  * methods for us:
  */
val multTupled = Function.tupled(mult _)
multTupled(d3)
val multUntupled = Function.untupled(multTupled)
multUntupled(d3._1,d3._2,d3._3)



