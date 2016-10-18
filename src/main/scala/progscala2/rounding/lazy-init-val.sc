// src/main/scala/progscala2/rounding/lazy-init-val.sc

/**
  * Cristiano:lazy val
  * A related scenario to by-name parameters is the case where you want
  * to evaluate an expression once to initialize a value, not repeatedly,
  * but you want to defer that invocation.
  *
  * The lazy keyword indicates that evaluation should be deferred
  * until the value is needed.
  * So, how is a lazy val different from a method call?
  * In a method call, the body is executed every time the method is invoked.
  *
  * For a lazy val, the initialization “body” is evaluated only once,
  * when the value is used for the first time.
  *
  * This one-time evaluation makes little sense for a mutable field.
  * Therefore, the lazy keyword is not allowed on vars.
  *
  */
object ExpensiveResource {
  lazy val resource: Int = init()  
  def init(): Int = { 
    // do something expensive
    0
  }
}
