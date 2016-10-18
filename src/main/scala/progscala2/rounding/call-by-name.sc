// src/main/scala/progscala2/rounding/call-by-name.sc

/**
  * Cristiano:Call by Name, Call by Value
  * Scala, like most languages, normally uses call-by-value semantics.
  * By-name parameters behave like functions; the expression is evaluated
  * every time it is used.
  *
  * <2> Define a continue function that accepts two argument lists.
  *     The first list takes a single, by-name parameter that is
  *     the conditional.
  *     The second list takes a single, by-name value that is the
  *     body to be evaluated for each iteration.
  *
  *
  *
  *
  *     Again: It’s important to note that the by-name parameters
  *     are evaluated every time they are referenced.
  *
  *     (By the way, this implementation shows how “loop” constructs
  *     can be replaced with recursion).
  *
  *     So, by-name parameters are in a sense lazy,
  *     because evaluation is deferred, but possibly repeated
  *     over and over again.
  *
  */

@annotation.tailrec                                                  // <1>
def continue(conditional: => Boolean)(body: => Unit) {               // <2>
  if (conditional) {                                                 // <3>
    body                                                             // <4>
    continue(conditional)(body)
  }
}

var count = 0                                                        // <5>
continue(count < 5) {
  println(s"at $count")
  count += 1
}
