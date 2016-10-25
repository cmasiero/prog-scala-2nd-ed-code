// src/main/scala/progscala2/fp/basics/hofs-closure3-example.sc

/**
  * Cristiano:Methods as Functions
  *
  * Despite the fact multiplier is now a method, we use it
  * just like a function, because it doesn’t reference this.
  *
  * When a method is used where a function is required,
  * we say that Scala lifts the method to be a function.
  *
  */

object Multiplier {
  var factor = 2
  // Compare: val multiplier = (i: Int) => i * factor
  def multiplier(i: Int) = i * factor
}

(1 to 10) filter (_ % 2 == 0) map Multiplier.multiplier reduce (_ * _)

Multiplier.factor = 3
(1 to 10) filter (_ % 2 == 0) map Multiplier.multiplier reduce (_ * _)

