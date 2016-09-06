// src/main/scala/progscala2/concurrency/akka/WorkerActor.scala
package progscala2.concurrency.akka
import scala.util.{Try, Success, Failure}
import akka.actor.{Actor, ActorLogging}

class WorkerActor extends Actor with ActorLogging {
  import Messages._

  private val datastore = collection.mutable.Map.empty[Long,String]  // <1> Receive handler is thread safe (Enforced by Akka itself).
                                                                     //     datastore is also private so datastore can be mutable collection
  def receive = {
    case Create(key, value) =>                                       // <2>  Create and send a response to sender
      datastore += key -> value
      sender ! Response(Success(s"$key -> $value added"))
    case Read(key) =>                                                // <3> Read and Response is in a Try (Failure,Success)
      sender ! Response(Try(s"${datastore(key)} found for key = $key"))
    case Update(key, value) =>                                       // <4> Update
      datastore += key -> value
      sender ! Response(Success(s"$key -> $value updated"))
    case Delete(key) =>                                              // <5> Delete
      datastore -= key
      sender ! Response(Success(s"$key deleted"))
    case Crash(_) => throw WorkerActor.CrashException                // <6> Crash the actor an throws the exception
    case DumpAll =>                                                  // <7> Response the state
      sender ! Response(Success(s"${self.path}: datastore = $datastore"))
  }
}

object WorkerActor {
  case object CrashException extends RuntimeException("Crash!")      // <8> The special crash exception used to simulate actor crashes
}

