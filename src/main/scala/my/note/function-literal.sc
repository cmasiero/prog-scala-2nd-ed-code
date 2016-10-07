/**
  * Cristiano:Function literals
  * Function literals are “anonymous” functions. In other languages,
  * they are variously called lambdas, closures, blocks, or procs.
  *
  * (s:String) => s.toUpperCase()
  * It takes an argument list with a single String argument named s.
  * The body of the function literal is after the “arrow,” =>.
  */

val strings = Array("antonio","mario")
val results = strings.map((s:String) => s.toUpperCase())
for (s <- strings){
  println(s)
}
for (s <- results){
  println(s)
}


/**
  * Cristiano:Function literals
  * (i: Int, s: String) => s+i
  * is a function literal of type
  * Function2[ Int, String, String] (String is returned).
  * You can even use the literal syntax for a type declaration.
  * The following declarations are equivalent:
  */
val f1: (Int, String) => String = (i, s) => s+i
val f2: Function2[Int, String, String] = (i, s) => s+i


