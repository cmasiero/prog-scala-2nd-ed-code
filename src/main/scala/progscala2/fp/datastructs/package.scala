// src/main/scala/progscala2/fp/datastructs/package.scala

/**
  *
  * Cristiano: Functional Data Structures - immutable - mutable - Seq
  * If user code includes the import statement import fp.datastructs._,
  * then when Seq instances are declared (without a package qualifier)
  * they will now use scala.collection.immutable.Seq instead of Scala’s
  * default scala.collection.Seq.
  */
package progscala2.fp
package object datastructs {
  type Seq[+A] = scala.collection.immutable.Seq[A]
  val Seq = scala.collection.immutable.Seq
}
