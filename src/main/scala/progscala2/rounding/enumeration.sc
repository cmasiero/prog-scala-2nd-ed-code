// src/main/scala/progscala2/rounding/enumeration.sc

/**
  * Cristiano:Enumeration
  *
  * While enumerations are a built-in part of many programming languages,
  * Scala takes a different route and implements them as an
  * Enumeration class in its standard library.
  * This means there is no special syntax for enumerations baked
  * into Scala’s grammar, as there is for Java.
  * Instead, you just define an object that extends the Enumeration class
  * and follow its idioms.
  *
  *
  * The type Breed is an alias that lets us reference Breed instead of Value.
  * The only place we actually use this is the argument to the isTerrier method.
  *
  */

object Breed extends Enumeration {
  type Breed = Value
  val doberman = Value("Doberman Pinscher")
  val yorkie   = Value("Yorkshire Terrier")
  val scottie  = Value("Scottish Terrier")
  val dane     = Value("Great Dane")
  val portie   = Value("Portuguese Water Dog")
}
import Breed._

// print a list of breeds and their IDs
println("ID\tBreed")
for (breed <- Breed.values) println(s"${breed.id}\t$breed")

// print a list of Terrier breeds
println("\nJust Terriers:")
Breed.values filter (_.toString.endsWith("Terrier")) foreach println

def isTerrier(b: Breed) = b.toString.endsWith("Terrier")

println("\nTerriers Again??")
Breed.values filter isTerrier foreach println
