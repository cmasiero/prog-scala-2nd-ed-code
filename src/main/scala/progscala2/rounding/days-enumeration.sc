// src/main/scala/progscala2/rounding/days-enumeration.sc

/**
  * Cristiano:Enumeration
  * You don’t actually see enumerations used a lot in Scala code,
  * especially compared to Java code.
  * Instead, case classes are often used when an “enumeration of values”
  * is needed. They are a bit more heavyweight, but they have advantages.
  *
  */
object WeekDay extends Enumeration {
  type WeekDay = Value
  val Mon, Tue, Wed, Thu, Fri, Sat, Sun = Value
}
import WeekDay._

def isWorkingDay(d: WeekDay) = ! (d == Sat || d == Sun)

WeekDay.values filter isWorkingDay foreach println
