// src/main/scala/progscala2/concurrency/akka/AkkaClient.scala
package progscala2.concurrency.akka
import akka.actor.{ActorRef, ActorSystem, Props}
import java.lang.{NumberFormatException => NFE}

object AkkaClient {                                                  // <1> Is an object we can define main here
  import Messages._
  // An actor system is a hierarchical group of actors which share common configuration ...
  private var system: Option[ActorSystem] = None                     // <2> We will use it in the shutdown logic

  def main(args: Array[String]) = {                                  // <3>
    processArgs(args)
    val sys = ActorSystem("AkkaClient")                              // <4> Create the Actor System and update
    system = Some(sys)                                               //     the system option
    val server = ServerActor.make(sys)                               // <5> Construct an instance of ServerActor by
                                                                     //     its companion object
    val numberOfWorkers =                                            // <6> Get how many workers to use from
      sys.settings.config.getInt("server.number-workers")            //     application.conf
    server ! Start(numberOfWorkers)                                  // <7> Send start message to the ServerActor to
                                                                     //     begin processing
    processInput(server)                                             // <8> Process command line input from the user
  }                                                                  //     Akka uses TypeSafe's Config library for
                                                                     //     configuartion values. The configuration
                                                                     //     file is src/main/resources/application.conf
  private def processArgs(args: Seq[String]): Unit = args match {
    case Nil =>
    case ("-h" | "--help") +: tail => exit(help, 0)
    case head +: tail => exit(s"Unknown input $head!\n"+help, 1)
  }

  
  private def processInput(server: ActorRef): Unit = {               // <9>
    val blankRE = """^\s*#?\s*$""".r
    val badCrashRE = """^\s*[Cc][Rr][Aa][Ss][Hh]\s*$""".r            // valid input crash or CrasH
    val crashRE = """^\s*[Cc][Rr][Aa][Ss][Hh]\s+(\d+)\s*$""".r       // valid input crash 10
    val dumpRE = """^\s*[Dd][Uu][Mm][Pp](\s+\d+)?\s*$""".r           // valid input
    val charNumberRE = """^\s*(\w)\s+(\d+)\s*$""".r
    val charNumberStringRE = """^\s*(\w)\s+(\d+)\s+(.+)$""".r
    
    def prompt() = print(">> ")                                      // <10> Define several nested methods for messaging
    def missingActorNumber() =                                       //      and for shutdown : 'def finished ...'
      println("Crash command requires an actor number.")
    def invalidInput(s: String) = 
      println(s"Unrecognized command: $s")
    def invalidCommand(c: String): Unit = 
      println(s"Expected 'c', 'r', 'u', or 'd'. Got $c")
    def invalidNumber(s: String): Unit = 
      println(s"Expected a number. Got $s")
    def expectedString(): Unit = 
      println("Expected a string after the command and number")
    def unexpectedString(c: String, n: Int): Unit =
      println(s"Extra arguments after command and number '$c $n'")
    def finished(): Nothing = exit("Goodbye!", 0)

    val handleLine: PartialFunction[String,Unit] = {                 // <11>
      case blankRE() =>   /* do nothing */
      case "h" | "help" => println(help)
      case dumpRE(n) =>                                              // <12>
        server ! (if (n == null) DumpAll else Dump(n.trim.toInt))
      case badCrashRE() => missingActorNumber()                      // <13>
      case crashRE(n) => server ! Crash(n.toInt)          
      case charNumberStringRE(c, n, s) => c match {                  // <14>
        case "c" | "C" => server ! Create(n.toInt, s)
        case "u" | "U" => server ! Update(n.toInt, s)
        case "r" | "R" => unexpectedString(c, n.toInt)
        case "d" | "D" => unexpectedString(c, n.toInt)
        case _ => invalidCommand(c)          
      }
      case charNumberRE(c, n) => c match {                           // <15>
        case "r" | "R" => server ! Read(n.toInt)
        case "d" | "D" => server ! Delete(n.toInt)
        case "c" | "C" => expectedString          
        case "u" | "U" => expectedString          
        case _ => invalidCommand(c)
      }
      case "q" | "quit" | "exit" => finished()                       // <16>
      case string => invalidInput(string)                            // <17>
    }
    
    while (true) {
      prompt()                                                       // <18>
      Console.in.readLine() match {
        case null => finished()
        case line => handleLine(line)
      }
    }
  }

  private val help =                                                 // <19>
  """Usage: AkkaClient [-h | --help]
    |Then, enter one of the following commands, one per line:
    |  h | help      Print this help message.
    |  c n string    Create "record" for key n for value string.
    |  r n           Read record for key n. It's an error if n isn't found.
    |  u n string    Update (or create) record for key n for value string.
    |  d n           Delete record for key n. It's an error if n isn't found.
    |  crash n       "Crash" worker n (to test recovery).
    |  dump [n]      Dump the state of all workers (default) or worker n.
    |  ^d | quit     Quit.
    |""".stripMargin

  private def exit(message: String, status: Int): Nothing = {        // <20>
    for (sys <- system) sys.shutdown()
    println(message)
    sys.exit(status)
  }
}
