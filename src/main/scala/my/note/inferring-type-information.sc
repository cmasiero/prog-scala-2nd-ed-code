import java.util.HashMap

/**
  * Cristiano:Inferring Type Information
  * before Java 7:
  * import java.util.HashMap;
  * ...
  * HashMap <Integer,String> intToStringMap = new HashMap<Integer, String>();
  *
  * after java 7:
  * HashMap <Integer,String> intToStringMap = new HashMap <>();
  *
  **/

/* In scala */
val intToStringMap: HashMap[Integer,String] = new HashMap
//more concise:
val intToStringMap2 = new HashMap[Integer,String]

/**
  * Cristiano:Method declaration (Inferring type)
  * Warning When the return type of a method is inferred and you
  * don’t use an equals sign before the opening curly brace
  * for the method body,
  * Scala infers a Unit return type, even when the last expression
  * in the method is a value of another type.
  * However, this behavior is too subtle and the mistake is easy to make.
  * Because it is easy enough to define a function that returns Unit,
  * the “procedure” syntax is now deprecated as of Scala 2.11. Don’t use it!
  *
  */

def doubleA( i: Int) {2 * i}
def doubleB( i: Int) = { 2 * i }

println(doubleA(2)) // print : ()
println(doubleB(2)) // print : 4


/**
  * () came from that was printed before we fixed the bug.
  * It is actually the real name of the single instance of the Unit type!
  */


