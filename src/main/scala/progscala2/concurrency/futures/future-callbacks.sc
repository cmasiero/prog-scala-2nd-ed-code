// src/main/scala/progscala2/concurrency/futures/future-callbacks.sc
import scala.concurrent.Future
import scala.concurrent.duration.Duration
import scala.concurrent.ExecutionContext.Implicits.global

case class ThatsOdd(i: Int) extends RuntimeException(                // <1>
  s"odd $i received!")

import scala.util.{Try, Success, Failure}                            // <2>

val doComplete: PartialFunction[Try[String],Unit] = {                // <3>
  case s @ Success(_) => println(s)                                  // <4>
  case f @ Failure(_) => println(f)
}

val futures = (0 to 9) map {                                         // <5>
  case i if i % 2 == 0 => Future.successful(i.toString)
  case i => Future.failed(ThatsOdd(i))
}


/**
  * Added by Cristiano, test callbacks...
  */

//futures(0).onComplete {
//  case Success(value) => println(s"Got the callback, meaning = $value")
//  case Failure(e) => e.printStackTrace
//}
//futures(2).onComplete {
//  case Success(value) => println(s"Got the callback, meaning = $value")
//  case Failure(e) => e.printStackTrace
//}

//for (i <- 0 until 9 ; if i % 2 == 0 ) {
//  if (futures(i).isCompleted) {
//    futures(i).onComplete {
//      case Success(value) => println(s"Got the callback, meaning = $value")
//      case Failure(e) => e.printStackTrace
//    }
//  }
//}

for (i <- 0 until 9) {
  futures(i).onComplete {
    case Success(value) => println(s"Got the callback, meaning = $value")
    case Failure(e) => println(s"Error message = ${e.getMessage}")
  }
}


futures map (_ onComplete doComplete)                                // <6>




