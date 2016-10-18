// src/main/scala/progscala2/patternmatching/match-deep-tuple.sc

/**
  * Cristiano:Pattern Matching, tuple
  *           Imagine we have a sequence of (String, Double) tuples
  *           for the names and prices of items in a store and we want
  *           to print them with their index.
  *           The Seq.zipWithIndex method is handy here.
  *           Note that the call to zipWithIndex returned tuples
  *           of the form (( name, cost), index).
  */
val itemsCosts = Seq(("Pencil", 0.52), ("Paper", 1.35), ("Notebook", 2.43))
val itemsCostsIndices = itemsCosts.zipWithIndex
for (itemCostIndex <- itemsCostsIndices) { 
  itemCostIndex match {
    case ((item, cost), index) => println(s"$index: $item costs $cost each")
  }
}
