// src/main/scala/progscala2/typelessdomore/partial-functions.sc

/*
 * Cristiano:Partial Function
 * Partial functions are partial in the sense that they aren’t defined for all possible inputs,
 * only those inputs that match at least one of the specified case clauses.
 *
 * Only case clauses can be specified in a partial function and the entire
 * function must be enclosed in curly braces.
 * In contrast, “regular” function literals can be wrapped in parentheses or curly braces.
 *
 * If the function is called with an input that doesn’t match one of the case clauses,
 * a MatchError is thrown at runtime.
 *
 * You can test if a PartialFunction will match an input using the isDefinedAt method.
 * This function avoids the risk of throwing a MatchError exception.
 *
 * You can “chain” PartialFunctions together: pf1 orElse pf2 orElse pf3 ….
 * If pf1 doesn’t match, then pf2 is tried, then pf3, etc.
 * A MatchError is only thrown if none of them matches.
 */

val pf1: PartialFunction[Any,String] = { case s:String => "YES" }    // <1>
val pf2: PartialFunction[Any,String] = { case d:Double => "YES" }    // <2>

val pf = pf1 orElse pf2                                              // <3>

def tryPF(x: Any, f: PartialFunction[Any,String]): String =          // <4>
  try { f(x).toString } catch { case _: MatchError => "ERROR!" }

def d(x: Any, f: PartialFunction[Any,String]) =                      // <5>
  f.isDefinedAt(x).toString

println("      |   pf1 - String  |   pf2 - Double  |    pf - All")   // <6>
println("x     | def?  |  pf1(x) | def?  |  pf2(x) | def?  |  pf(x)")
println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++")
List("str", 3.14, 10) foreach { x =>
  printf("%-5s | %-5s | %-6s  | %-5s | %-6s  | %-5s | %-6s\n", x.toString, 
    d(x,pf1), tryPF(x,pf1), d(x,pf2), tryPF(x,pf2), d(x,pf), tryPF(x,pf))
}

