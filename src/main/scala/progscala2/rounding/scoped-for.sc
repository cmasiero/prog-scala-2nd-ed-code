// src/main/scala/progscala2/rounding/scoped-for.sc


/*
 * Cristiano:for comprehensions Expanded Scope and Value Definitions
 * Feature of Scala’s for comprehensions is the ability to define values
 * inside the first part of your for expressions that can be used
 * in the later expressions.
 *
 * Note that upcasedBreed is an immutable value, but the val keyword
 * isn’t required.
 */

val dogBreeds = List("Doberman", "Yorkshire Terrier", "Dachshund",
                     "Scottish Terrier", "Great Dane", "Portuguese Water Dog")
for {
  breed <- dogBreeds
  upcasedBreed = breed.toUpperCase()
} println(upcasedBreed)
