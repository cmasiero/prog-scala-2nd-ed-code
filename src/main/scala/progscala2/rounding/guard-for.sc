// src/main/scala/progscala2/rounding/guard-for.sc

val dogBreeds = List("Doberman", "Yorkshire Terrier", "Dachshund",
                     "Scottish Terrier", "Great Dane", "Portuguese Water Dog")

/**
  * Cristiano:for Comprehensions - Guards: Filtering Values
  */
for (breed <- dogBreeds if breed.contains("Terrier"))
  println(breed)
