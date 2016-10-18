// src/main/scala/progscala2/patternmatching/scoped-option-for.sc

val dogBreeds = Seq(Some("Doberman"), None, Some("Yorkshire Terrier"), 
                    Some("Dachshund"), None, Some("Scottish Terrier"),
                    None, Some("Great Dane"), Some("Portuguese Water Dog"))

println("second pass:")

/**
  * Cristiano:for comprehensions None ...
  * Doesn’t None throw an exception if you try to extract an object from it?
  * Yes, but the comprehension effectively checks for this case and skips the Nones.
  * It’s as if we added
  * an explicit if breedOption != None before the second line.
  */

for {
  Some(breed) <- dogBreeds
  upcasedBreed = breed.toUpperCase()
} println(upcasedBreed)
