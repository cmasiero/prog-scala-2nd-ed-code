/**
  * Cristiano: Implicit Conversions
  *
  * There are several ways to create a pair:
  * (1, "one")
  * 1 -> "one"
  * 1 → "one" // Using → instead of -> Tuple2( 1, "one")
  * Pair( 1, "one") // Deprecated as of Scala 2.11
  *
  * Scala knows nothing about a -> b
  *
  * This “literal” form is actually a method -> and a special Scala feature, implicit conversions,
  * which allows us to use this method between two values of any type.
  *
  * The trick is to use a “wrapper” object that has -> defined. Scala has one in Predef already:
  *
  */
implicit final class ArrowAssoc[A](val self: A) {
  def -> [B](y: B): Tuple2[A, B] = Tuple2( self, y)
}

/**
  * Because ArrowAssoc is declared implicit, the
  * compiler goes through the following logical steps:
  * It sees that we’re trying to call a -> method on String (e.g., "one" -> 1).
  * Because String has no -> method, it looks for an implicit conversion in scope to a type that has this method.
  * It finds ArrowAssoc.
  * It constructs an ArrowAssoc, passing it the "one" string. It then resolves the -> 1 part of the expression
  * and confirms the whole expression’s type matches the expectation of Map.apply, which is a pair instance.
  *
  *
  * Indiscriminate use of implicit methods can lead to mysterious behavior that is hard to debug.
  * For this reason, implicit methods are considered an optional feature as of Scala 2.10,
  * so you should enable the feature explicitly with the import statement,
  * import scala.language.implicitConversions, or with the global -language:implicitConversions compiler option.
  *
  */