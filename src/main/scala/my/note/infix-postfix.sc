/**
  * Cristiano:infix an postfix notation
  *
  * 1 + 2 is the same as 1.+( 2),
  * because of the “infix” notation where we can drop
  * the period and parentheses for single-argument methods.
  *
  * Similarly, a method with no arguments can be invoked without the period.
  * This is called “postfix” notation.
  */

// This avoid warning message about postfix invocation.
import scala.language.postfixOps


//1 + 2

1.toString // normal invocation

1 toString // postfix invocation

