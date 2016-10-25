// src/main/scala/progscala2/fp/datastructs/vector.sc
/**
  * Cristiano:Functional Data Structures - Vector
  * While List is a venerable choice for a sequence, consider using immutable.
  * Vector instead, because it provides O(1) (constant time) performance
  * for all operations, while List is O(n) for all operations
  * that don’t just involve accessing the head element.
  *
  */
val vect1 = Vector("Programming", "Scala")
val vect2 = "People" +: "should" +: "read" +: vect1
println(vect2)

val vect3 = "Programming" +: "Scala" +: Vector.empty[String]
val vect4 = "People" +: "should" +: "read" +: Vector.empty[String]
val vect5 = vect4 ++ vect3
println(vect5)

/**
  * Cristiano:Functional Data Structures - immutable - mutable - collection
  * To encourage the use of immutable collections, Predef and several other
  * types it uses expose several immutable collection types.
  * However, Predef also brings scala.collection.Seq into scope,
  * an exception to the rule of only exposing immutable collections.
  * The types in scala.collection are abstractions shared by both
  * immutable collections and by mutable collections.
  * Although there is a scala.collection.immutable.Seq
  * (which subclasses scala.collection.Seq), the primary reason
  * for exposing scala.collection.Seq instead is to make it easy
  * to treat Java Arrays.
  *
  * ******************************* Warning ******************************
  * Keep in mind that the default Seq type is actually scala.collection.Seq.
  * So, an instance of Seq passed to your code could be mutable
  * and therefore thread-unsafe.
  * **********************************************************************
  *
  * If you really want to enforce usage of scala.collection.immutable.Seq
  * instead, use the following technique :
  * src/main/scala/progscala2/fp/datastructs/package.scala
  *
  *
  */