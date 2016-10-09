/**
  *
  * Cristiano:sealed & final
  *
  * The sealed keyword tells the compiler that all subclasses must be declared in the same source file.
  *
  * Some and None are declared in the same file with Option in the Scala library.
  * This technique effectively prevents additional subtypes of Option.
  * You can also declare a type final if you want to prevent users from subtyping it.
  */

// sealed abstract class Option[+A] ... { ... }


