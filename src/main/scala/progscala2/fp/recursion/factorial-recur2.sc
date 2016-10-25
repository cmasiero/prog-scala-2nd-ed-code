// src/main/scala/progscala2/fp/recursion/factorial-recur2.sc
import scala.annotation.tailrec

/**
  * Cristiano: recursion - Tail Calls and Tail-Call Optimization
  *
  * A particular kind of recursion is called tail-call self-recursion,
  * which occurs when a function calls itself and the call is the final
  * (“tail”) operation it does.
  *
  * Tail-call self-recursion is very important because it is the easiest
  * kind of recursion to optimize by conversion into a loop.
  *
  * Loops eliminate the potential of a stack overflow, and they
  * improve performance by eliminating the recursive function call overhead.
  *
  * Scala has an annotation, @tailrec, you can add to recursive functions
  * that you think are tail-call recursive.
  * If the compiler can’t optimize them, it will throw an exception.
  *
  *
  * ********** Warning **********
  * The tail-call optimization won’t be applied when a method that calls itself
  * might be overridden in a derived type.
  * Hence, the recursive method must be defined with the private or final keyword,
  * or it must be nested in another method.
  *
  */


def factorial(i: BigInt): BigInt = {
  @tailrec
  def fact(i: BigInt, accumulator: BigInt): BigInt =
    if (i == 1) accumulator
    else fact(i - 1, i * accumulator)

  fact(i, 1)
}

for (i <- 1 to 10)
  println(s"$i:\t${factorial(i)}")
