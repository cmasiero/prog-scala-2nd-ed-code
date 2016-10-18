// BEGIN SERVICE
// src/main/scala/progscala2/rounding/traits.sc

/**
  * Cristiano:traits
  * Scala replaces interfaces with traits.
  * We’ll explore them in glorious detail in Chapter   9, but for now,
  * think of them of interfaces that also give you the option of defining
  * the methods you declare.
  * Traits can also declare and optionally define instance fields
  * (not just static fields, as in Java interfaces),
  * and you can declare and optionally define type values,
  *
  */


class ServiceImportante(name: String) {
  def work(i: Int): Int = {
    println(s"ServiceImportante: Doing important work! $i")
    i + 1
  }
}

val service1 = new ServiceImportante("uno")
(1 to 3) foreach (i => println(s"Result: ${service1.work(i)}"))
// END SERVICE

// BEGIN LOGGING
trait Logging {
  def info   (message: String): Unit
  def warning(message: String): Unit
  def error  (message: String): Unit
}

trait StdoutLogging extends Logging {
  def info   (message: String) = println(s"INFO:    $message")
  def warning(message: String) = println(s"WARNING: $message")
  def error  (message: String) = println(s"ERROR:   $message")
}
// END LOGGING

// BEGIN MIXED
val service2 = new ServiceImportante("dos") with StdoutLogging {
  override def work(i: Int): Int = {
    info(s"Starting work: i = $i")
    val result = super.work(i)
    info(s"Ending work: i = $i, result = $result")
    result
  }
}
(1 to 3) foreach (i => println(s"Result: ${service2.work(i)}"))

// END MIXED
