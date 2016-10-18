/**
  * Cristiano: apply - unapply Method
  *            case class gets a companion object that has a factory method
  *            named apply, which is used for construction.
  *            Using “symmetry” arguments, we might infer that there must be
  *            another method generated called unapply,
  *            which is used for extraction or “deconstruction.”
  *            Indeed there is such an extractor method and it is invoked
  *            when a pattern-match expression like this is encountered:
  */


/*
person match {
  case Person("Alice", 25, Address(_, "Chicago", _)) => ...
  ...
}
*/

/**
  * Scala looks for Person.unapply(…) and Address.unapply(…) and calls them.
  * the Person companion object that the compiler generates looks like this:
  */
/*
object Person {
  def apply(name: String, age: Int, address: Address) =
    new Person(name, age, address)
  def unapply( p: Person): Option[Tuple3[ String, Int, Address]] =
    Some((p.name, p.age, p.address)) ...
}
*/

/**
  * Just note that the extracted fields are returned in a Some wrapping
  * a Tuple3.
  * The compiler then extracts those tuple elements for comparison
  * with literal values,
  */

/**
  * Note: To gain some performance benefits, Scala 2.11.1 relaxed
  * the requirement that unapply return an Option[T].
  * It can now return any type as long as it has the following methods:
  * def isEmpty: Boolean
  * def get: T
  */