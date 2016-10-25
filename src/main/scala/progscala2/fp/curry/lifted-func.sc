// src/main/scala/progscala2/fp/curry/lifted-func.sc

/**
  * Cristiano:Currying and Other Transformations on Functions
  * Finally, there are transformations between partial
  * functions and functions that return options.
  *
  * Here is another use for the concept of lifting a function.
  * If we have a partial function and we don’t like the idea
  * of an exception being thrown, we can lift the function
  * into one that returns an Option instead.
  *
  * We can also “unlift” a function that returns an option
  * to create a partial function.
  *
  */

val finicky: PartialFunction[String,String] = {
  case "finicky" => "FINICKY"
}

val finickyOption = finicky.lift

finicky("finicky")
try {
  finicky("other")
} catch {
  case e: scala.MatchError => e
}

finickyOption("finicky")
finickyOption("other")

val finicky2 = Function.unlift(finickyOption)

finicky2("finicky")
try {
  finicky2("other")
} catch {
  case e: scala.MatchError => e
}
