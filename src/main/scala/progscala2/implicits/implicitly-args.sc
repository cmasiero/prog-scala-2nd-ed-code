// src/main/scala/progscala2/implicits/implicitly-args.sc
import math.Ordering

/**
  * Cristiano:implicitly
  *
  * Predef defines a method called implicitly.
  * Combined with a type signature addition, it provides a useful shorthand
  * way of defining method signatures that take a single implicit argument,
  * where that argument is a parameterized type.
  *
  * MyList shows two alternative ways of writing a method like sortBy.
  * The first implementation, sortBy1, uses the syntax we already know.
  * The method takes an additional implicit value of type Ordering[B].
  *
  * For sortBy1 to be used, there must be an instance in scope that knows
  * how to “order” instances of the desired type B.
  * We say that B is bound by a “context,” in this case, the ability
  * to order instances.
  *
  * This idiom is so common that Scala provides a shorthand syntax,
  * which is used by the second implementation, sortBy2.
  * The type parameter B : Ordering is called a context bound.
  * It implies the second, implicit argument list that takes an Ordering[B]
  * instance.
  * However, we need to access this Ordering instance in the method,
  * but we no longer have a name for it, because it’s no longer explicitly
  * declared in the source code.
  * That’s what Predef.implicitly does for us.
  *
  * Whatever instance is passed to the method for the implicit argument
  * is resolved by implicitly.
  * Note the type signature that it requires, Ordering[ B] in this case.
  */

case class MyList[A](list: List[A]) {
  def sortBy1[B](f: A => B)(implicit ord: Ordering[B]): List[A] =
    list.sortBy(f)(ord)

  def sortBy2[B : Ordering](f: A => B): List[A] =
    list.sortBy(f)(implicitly[Ordering[B]])
}

val list = MyList(List(1,3,5,2,4))

list sortBy1 (i => -i)
list sortBy2 (i => -i)

/**
  * Cristiano:implicitly - note
  * The combination of a context bound and the implicitly method
  * is a shorthand for the special case where we need an implicit argument
  * of a parameterized type, where the type parameter is one of the other
  * types in scope (for example, [B : Ordering]
  * for an implicit Ordering[B] parameter).
  */
