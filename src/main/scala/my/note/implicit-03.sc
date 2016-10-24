/**
  * Cristiano:Implicit
  * Working Around Erasure
  * Another example where the implicit object only provides evidence is a
  * technique for working around limitations due to type erasure.
  *
  * def m(seq: Seq[Int]): Unit = ...
  * def m(seq: Seq[String]): Unit = ...
  * Those definitions produce and error :
  * < console >: 8: error: double definition:
  *
  * we can add an implicit argument to disambiguate the methods:
  * def m(seq: Seq[ Int])( implicit i: IntMarker.type): Unit = ...
  * def m(seq: Seq[ String])( implicit s: StringMarker.type): Unit = ...
  *
  *
  * Warning:
  * Avoid using implicit arguments and corresponding values of common
  * types like Int and String.
  * It’s more likely that implicits of such types will be defined
  * in multiple places and cause confusing bugs or compilation
  * errors when they are imported into the same scope.
  *
  *
  * Improving Error Messages
  * @implicitNotFound(msg =
  *                  "Cannot construct a collection of type ${ To} with
  *                  elements of type ${ Elem}" +
  *                  " based on a collection of type ${ From}.")
  * trait CanBuildFrom[-From, -Elem,   To] {...}
  *
  * You can only use this annotation on types intended for use as
  * implicit values for satisfying implicit arguments.
  * You can’t use it to annotate methods that take implicit arguments,
  * like our SRow.get[T] method.
  */