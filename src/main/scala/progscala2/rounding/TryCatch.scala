// src/main/scala/progscala2/rounding/TryCatch.scala
package progscala2.rounding


/**
  * Cristiano:try, catch, and finally Clauses
  *
  * Unlike Java, Scala does not have checked exceptions, which are now regarded as an unsuccessful design.
  * Java’s checked exceptions are treated as unchecked by Scala.
  * There is also no throws clause on method declarations.
  * However, there is a @throws annotation that is useful for Java interoperability.
  *
  * <2> scala.util.control.NonFatal for matching on “nonfatal” exceptions.
  * <4> Declare the source to be an Option, so we can tell in the finally
  *     clause if we have an actual instance or not.
  * <6> Source.fromFile will throw a java.io.FileNotFoundException if the file doesn’t exist.
  *     Otherwise, wrap the returned Source instance in a Some.
  *     Calling get on the next line is safe, because if we’re here, we know we have a Some.
  */

object TryCatch {
  /** Usage: scala rounding.TryCatch filename1 filename2 ... */
  def main(args: Array[String]) = {
    args foreach (arg => countLines(arg))                            // <1>
  }

  import scala.io.Source                                             // <2>
  import scala.util.control.NonFatal

  def countLines(fileName: String) = {                               // <3>
    println()  // Add a blank line for legibility
    var source: Option[Source] = None                                // <4>
    try {                                                            // <5>
      source = Some(Source.fromFile(fileName))                       // <6>
      val size = source.get.getLines.size
      println(s"file $fileName has $size lines")
    } catch {
      case NonFatal(ex) => println(s"Non fatal exception! $ex")      // <7>
    } finally {
      for (s <- source) {                                            // <8>
        println(s"Closing $fileName...")
        s.close
      }
    }
  }
}
