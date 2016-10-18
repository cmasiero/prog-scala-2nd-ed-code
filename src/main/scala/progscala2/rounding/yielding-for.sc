// src/main/scala/progscala2/rounding/yielding-for.sc

val dogBreeds = List("Doberman", "Yorkshire Terrier", "Dachshund",
  "Scottish Terrier", "Great Dane", "Portuguese Water Dog")

/**
  * Cristiano:for Comprehensions - Yielding
  * Every time through the for expression,
  * the filtered result is yielded as a value named breed.
  * These results accumulate with every run.
  */
val filteredBreeds = for {
  breed <- dogBreeds
  if breed.contains("Terrier") && !breed.startsWith("Yorkshire")
} yield breed

for ( x <- filteredBreeds)
  println("--->" + x)


/**
  * Cristiano:for loop
  * When a for comprehension doesn’t use yield,
  * but performs side effects like printing instead,
  * the comprehension is called a for loop,
  **/

