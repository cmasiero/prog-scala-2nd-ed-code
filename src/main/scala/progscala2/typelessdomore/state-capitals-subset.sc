// src/main/scala/progscala2/typelessdomore/state-capitals-subset.sc




val stateCapitals = Map(
  "Alabama" -> "Montgomery",
  "Alaska"  -> "Juneau",
  // ...
  "Wyoming" -> "Cheyenne")
    
println( "Get the capitals wrapped in Options:" )
println( "Alabama: " + stateCapitals.get("Alabama") )
println( "Wyoming: " + stateCapitals.get("Wyoming") )
println( "Unknown: " + stateCapitals.get("Unknown") )

println( "Get the capitals themselves out of the Options:" )
println( "Alabama: " + stateCapitals.get("Alabama").get )

/**
  * Cristiano:getOrElse
  * The Map.get method returns an Option[ T], where T is String in this case.
  * By returning an Option, we can’t “forget” that we have to verify that
  * something was returned.
  *
  * The values returned by Map.get are automatically wrapped in a Some,
  * when there is a value in the map for the specified key.
  * Conversely, when we ask for a map entry that doesn’t exist,
  * the None object is returned, rather than null.
  *
  * Option.get is a bit dangerous. If the Option is a Some, Some.get returns the value.
  * However, if the Option is actually None, then None.get throws a NoSuchElementException.
  *
  * So, getOrElse is the more defensive of the two methods. It avoids a potential thrown exception.
  */
println( "Wyoming: " + stateCapitals.get("Wyoming").getOrElse("Oops!") )
println( "Unknown: " + stateCapitals.get("Unknown").getOrElse("Oops2!") )
