// src/main/scala/progscala2/fp/datastructs/map.sc
/**
  * Cristiano: Functional Data Structures - map
  *
  * We learned previously that the key -> value idiom is actually
  * implemented with an implicit conversion in the library.
  */

val stateCapitals = Map(
  "Alabama" -> "Montgomery",
  "Alaska"  -> "Juneau",
  "Wyoming" -> "Cheyenne")

val lengths = stateCapitals map { 
  kv => (kv._1, kv._2.length) 
}
println(lengths)

val caps = stateCapitals map { 
  case (k, v) => (k, v.toUpperCase)
}
println(caps)

val stateCapitals2 = stateCapitals + ("Virginia" -> "Richmond")
println(stateCapitals2)

val stateCapitals3 = stateCapitals2 + (
  "New York" -> "Albany", "Illinois" -> "Springfield")
println(stateCapitals3)
