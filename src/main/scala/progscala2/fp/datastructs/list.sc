// src/main/scala/progscala2/fp/datastructs/list.sc

/**
  * Cristiano:Functional Data Structures - List
  * You can construct a list with the List.apply method,
  * then prepend additional elements with the :: method,
  * called “cons” (for “construct”), creating new lists.
  */

val list1 = List("Programming", "Scala")                             // <1>
val list2 = "People" :: "should" :: "read" :: list1                  // <2>

/**
  * We can also use the :: method to construct a list by
  * prepending elements to the Nil empty list:
  *
  * Nil is equivalent to List.empty[ Nothing].
  */

val list3 = "Programming" :: "Scala" :: Nil                          // <3>
val list4 = "People" :: "should" :: "read" :: Nil


/**
  * Finally, you can concatenate two lists (or any sequence)
  * together using the method:
  */
val list5 = list4 ++ list3                                           // <4>

/**
  * Cristiano: Functional Data Structures - methods - function - List - Vector
  * While it’s still common to construct lists when appropriate,
  * it is not recommended that methods be defined to take List
  * arguments or return List values.
  * Instead, use Seq, so that an instance of any subtype of
  * Seq can be used, including List and Vector.
  */
