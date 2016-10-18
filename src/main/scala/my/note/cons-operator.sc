/**
  * Cristiano:Matching on case Classes, +: (cons-constructor) operator
  * Can be used to construct a new sequence by prepending an element
  * to an existing sequence.
  */

val list = 1 +: 2 +: 3 +: 4 +: Nil

/**
  * Cristiano:Matching on case Classes, +: special singleton object (unapply)
  *
  * If the construction of sequences is done with a method named +:,
  * how is extraction done with the same syntax?
  * To do that, the Scala library defines a special singleton object named  +:.
  * Yes, the name is “+:”.
  * Like methods, types can have names with a wide variety of characters.
  * It has just one method, the unapply method the compiler needs
  * for our extraction case statement.
  *
  *
  * As a matter of fact, we can write it that way:
  * def processSeq2[ T]( l: Seq[ T]): Unit = l match {
  *     case  +:(head, tail) => ...
  *
  * But we can also use infix notation, head +: tail,
  * because the compiler exploits another bit of syntactic sugar.
  *
  *
  * There is also a similar object for Lists, :: .
  */