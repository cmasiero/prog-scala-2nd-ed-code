/**
  *
  * Cristiano:import
  * You can rename types as you import them,
  * and you can suppress the visibility of unwanted types:
  */
def stuffWithBigInteger() = {
  import java.math.BigInteger.{
  ONE => _,
  TEN,
  ZERO => JAVAZERO}

  // println( "ONE: "  ONE )
  // ONE is effectively undefined
  println( "TEN: " + TEN )
  println( "ZERO: "  + JAVAZERO )
}


stuffWithBigInteger()

/**
  * Renaming the java.math.BigInteger.ONE constant to underscore (_) makes it invisible and unavailable.
  * Use this technique when you want to import everything except a few items.
  */

