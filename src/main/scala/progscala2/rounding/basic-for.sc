// src/main/scala/progscala2/rounding/basic-for.sc

/**
  * Cristiano:Scala for Comprehensions
  * Another familiar control structure that’s particularly feature-rich in Scala is
  * the for loop, called the for comprehension or for expression.
  * Actually, the term comprehension comes from functional programming.
  * It expresses the idea that we are traversing one or more collections of some kind,
  * “comprehending” what we find, and computing something new from it,
  * often another collection.
  */

val dogBreeds = List("Doberman", "Yorkshire Terrier", "Dachshund",
                     "Scottish Terrier", "Great Dane", "Portuguese Water Dog")

/**
  * Cristiano:The expression breed <- dogBreeds is called a generator expression.
  */
for (breed <- dogBreeds)
  println(breed)
