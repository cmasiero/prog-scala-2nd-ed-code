// src/main/scala/progscala2/concurrency/akka/ServerActor.scala
package progscala2.concurrency.akka
import scala.util.{Try, Success, Failure}
import scala.util.control.NonFatal
import scala.concurrent.duration._
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import akka.actor.{Actor, ActorLogging, ActorRef, 
  ActorSystem, Props, OneForOneStrategy, SupervisorStrategy}
import akka.pattern.ask
import akka.util.Timeout


class ServerActor extends Actor with ActorLogging {                  // <1> // Mix in the ActorLogging trait, add log field.
  import Messages._

  implicit val timeout = Timeout(1.seconds)

  override val supervisorStrategy: SupervisorStrategy = {            // <2> Override default supervisor
    val decider: SupervisorStrategy.Decider = {
      case WorkerActor.CrashException => SupervisorStrategy.Restart  //     If you simulate crash, restart occurs
      case NonFatal(ex) => SupervisorStrategy.Resume                 //     If non fatal Exception occurs continue (Risk)
    }
    OneForOneStrategy()(decider orElse super.supervisorStrategy.decider)    // If decider doesn't have an error ...
  }

  var workers = Vector.empty[ActorRef]                               // <3> // Keep track of the worker actors

  def receive = initial                                              // <4> // receive : the initial request handler

  val initial: Receive = {                                           // <5> Initial alias PartialFunction[Any,Unit].
    case Start(numberOfWorkers) =>                                   //     Manage only initial Start message to the actor
      workers = ((1 to numberOfWorkers) map makeWorker).toVector
      context become processRequests                                 // <6> When Start is received, construct the workers
  }                                                                  //     and transition to the second state of the state machine
                                                                     //     'processRequests'

  val processRequests: Receive = {                                   // <7> This Receive block handles the rest of the message after Start
                                                                     //
    case c @ Crash(n) => workers(n % workers.size) ! c
    case DumpAll =>                                                  // <8> Dump need to be forwarded to all workers, we get all the responses
      Future.fold(workers map (_ ? DumpAll))(Vector.empty[Any])(_ :+ _) // from the workers together in a result message . We do that with
        .onComplete(askHandler("State of the workers"))                 // the ask pattern akka.pattern.ask
    case Dump(n) =>                                                     // ? send a Message and return a Future ... (see book page 438)
      (workers(n % workers.size) ? DumpAll).map(Vector(_))
        .onComplete(askHandler(s"State of worker $n"))
    case request: Request =>                                         // CRUD commands, the Request = 'trait of Create,Read ecc in Message.scala'
      val key = request.key.toInt
      val index = key % workers.size
      workers(index) ! request
    case Response(Success(message)) => printResult(message)          // Response message from workers
    case Response(Failure(ex)) => printResult(s"ERROR! $ex") 
  }

  def askHandler(prefix: String): PartialFunction[Try[Any],Unit] = {
    case Success(suc) => suc match {
      case vect: Vector[_] => 
        printResult(s"$prefix:\n")
        vect foreach {
          case Response(Success(message)) => 
            printResult(s"$message")
          case Response(Failure(ex)) => 
            printResult(s"ERROR! Success received wrapping $ex")      
        }
      case _ => printResult(s"BUG! Expected a vector, got $suc")
    }
    case Failure(ex) => printResult(s"ERROR! $ex")      
  }

  protected def printResult(message: String) = {
    println(s"<< $message")
  }

  protected def makeWorker(i: Int) =
    context.actorOf(Props[WorkerActor], s"worker-$i")
}

object ServerActor {                                                 // <9> The companion Object provide a make method to
  def make(system: ActorSystem): ActorRef =                          //     construct the actor
    system.actorOf(Props[ServerActor], "server")
}
