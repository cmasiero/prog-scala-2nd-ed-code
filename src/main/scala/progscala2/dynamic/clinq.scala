// src/main/scala/progscala2/dynamic/CLINQ.scala
package progscala2.dynamic
import scala.language.dynamics                                       // <1> dynamics is and optional language feature
                                                                     //     so, we import it to enable it
case class CLINQ[T](records: Seq[Map[String,T]]) extends Dynamic {

  def selectDynamic(name: String): CLINQ[T] =                        // <2> Used for the projections of fields
    if (name == "all" || records.length == 0) this                   // <3> Return all the fields for 'all' or for no records
    else {
      val fields = name.split("_and_")                               // <4> _and_ joins two or more fields. We split them into an array
      for (f <- fields)  println("CLINQ:selectDynamic field : " + f)
      val seed = Seq.empty[Map[String,T]]
      val newRecords = (records foldLeft seed) {
        (results, record) =>
          val projection = record filter {                           // <5> Filter returns just the named fields
            case (key, value) => fields contains key
          }
          // Drop records with no projection.
          if (projection.size > 0) results :+ projection
          else results
      }
      println("CLINQ:selectDynamic seed.size : " + seed.size)
      for (s <- seed)  println("CLINQ:selectDynamic seed : " + s)
      CLINQ(newRecords)                                              // <6> return a new CLINQ
    }


  def applyDynamic(name: String)(field: String): Where = name match {
    case "where" => new Where(field)                                 // <7>
    case _ => throw CLINQ.BadOperation(field, """Expected "where".""")
  }

  protected class Where(field: String) extends Dynamic {             // <8>
    def filter(value: T)(op: (T,T) => Boolean): CLINQ[T] = {         // <9>
      val newRecords = records filter {
        _ exists {
          case (k, v) => field == k && op(value, v)
        }
      }
      CLINQ(newRecords)
    }

    def applyDynamic(op: String)(value: T): CLINQ[T] = op match {
      case "EQ" => filter(value)(_ == _)                             // <10>
      case "NE" => filter(value)(_ != _)                             // <11>
      case _ => throw CLINQ.BadOperation(field, """Expected "EQ" or "NE".""")
    }
  }

  override def toString: String = records mkString "\n"              // <12>
}

object CLINQ {                                                       // <13>
  case class BadOperation(name: String, msg: String) extends RuntimeException(
    s"Unrecognized operation $name. $msg")
}
