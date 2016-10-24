/**
  * Cristiano:implicit
  * Scenarios for Implicit Arguments
  * The first category is boilerplate elimination,
  * such as providing context information implicitly rather than explicitly.
  *
  * The second category includes constraints that reduce bugs or limit the
  * allowed types that can be used with certain methods with parameterized types.
  *
  * execution context :
  * Passing an “execution context” is one recommended use of implicit arguments.
  * Other example contexts include transactions, database connections, thread pools,
  * and user sessions.
  *
  * capabilities:
  * Besides passing contexts, implicit arguments can be used to control capabilities.
  * For example, an implicit user session argument might contain authorization
  * tokens that control whether or not certain API operations can be invoked
  * on behalf of the user or to limit data visibility.
  * Suppose you are constructing a menu for a user interface and some menu items
  * are shown only if the user is logged in, while others are shown only if the user
  * isn’t logged in:
  *
  *
  * Constraining Allowed Instances
  * Suppose we have a method with parameterized types and we want to constrain
  * the allowed types that can be used for the type parameters.
  * If the types we want to permit are all subtypes of a common supertype,
  * we can use object-oriented techniques and avoid implicits.
  * This technique doesn’t help when there is no common superclass.
  * For that situation, we can use an implicit argument to limit the allowed types.
  * The Scala collections API does this to solve a design problem.
  * Many of the methods supported by the concrete
  * collections classes are implemented by parent types.
  * For example, List[ A]. map( f: A ⇒ B): List[ B] creates a new list after
  * applying the function f to each element.
  * The map method is supported by most collections.
  * Therefore, it makes sense to implement map once in a generic trait,
  * then mix that trait into all the collections that need it.
  * However, we want to return the same collection type we started with,
  * so how can we tell the one map method to do that?
  * The Scala API uses a convention of passing a “builder”
  * as an implicit argument to map.
  *     trait TraversableLike[A,Repr] extends ... {
  *       ...
  *       def map[B,That](f: A = > B)(
  *          implicit bf: CanBuildFrom[ Repr, B, That]): That =
  *          ...
  * CanBuildFrom is our builder.
  * It’s named that way to emphasize the idea that you can build
  * any new collection you want, as long as an implicit builder object exists.
  * Repr is the type of the actual collection used internally to hold the items.
  * B is the type of elements created by the function f.
  * That is the type parameter of the target collection we want to create.
  * If you implement your own collections, you’ll want to reuse method implementations
  * like TraversableLike.map, so you’ll need to create your own CanBuildFrom
  * types and import implicit instances of them in code that uses your collections.
  *
  */


