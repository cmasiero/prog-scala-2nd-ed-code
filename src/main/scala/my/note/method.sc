/**
  *
  * Cristiano:Methods with Empty Argument Lists (-Xlint)
  *
  * A convention in the Scala community is to omit parentheses for no-argument methods
  * that have no side effects, like the size of a collection.
  * When the method performs side effects, parentheses are usually added,
  * offering a small “caution signal” to the reader that mutation might occur,
  * requiring extra care.
  * If you use the option -Xlint when you invoke scala or scalac,
  * it will issue a warning if you define a method with no parentheses
  * that performs side effects (e.g., I/ O).
  * I’ve added that flag to our SBT build.
  *
  */
