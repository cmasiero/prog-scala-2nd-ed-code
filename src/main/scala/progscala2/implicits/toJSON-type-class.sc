// src/main/scala/progscala2/implicits/toJSON-type-class.sc

/**
  * Cristiano: Type Class Pattern
  * The Type Class Pattern in Scala adds the missing interface piece that
  * our example implementations of implicit conversions so far
  * didn’t provide.
  * Scala doesn’t allow the implicit and case keywords together.
  * This capability is called ad hoc polymorphism,
  * because the polymorphic behavior of toJSON is not tied to the type
  * system, as in subtype polymorphism, the conventional object-oriented
  * inheritance.
  *
  * The Type Class Pattern is ideal for situations where certain clients
  * will benefit from the “illusion” that a set of classes provide
  * a particular behavior that isn’t useful for the majority of clients.
  *
  */

/**
  * Cristiano:wide use of implicits
  * One way to improve their visibility is to adopt the practice of putting
  * implicit values in a special package named implicits
  * or an object named Implicits.
  */

case class Address(street: String, city: String)
case class Person(name: String, address: Address)

trait ToJSON {
  def toJSON(level: Int = 0): String

  val INDENTATION = "  "
  def indentation(level: Int = 0): (String,String) = 
    (INDENTATION * level, INDENTATION * (level+1))
}

implicit class AddressToJSON(address: Address) extends ToJSON {
  def toJSON(level: Int = 0): String = {
    val (outdent, indent) = indentation(level)
    s"""{
      |${indent}"street": "${address.street}",
      |${indent}"city":   "${address.city}"
      |$outdent}""".stripMargin
  }
}


implicit class PersonToJSON(person: Person) extends ToJSON {
  def toJSON(level: Int = 0): String = {
    val (outdent, indent) = indentation(level)
    s"""{
      |${indent}"name":    "${person.name}", 
      |${indent}"address": ${person.address.toJSON(level + 1)} 
      |$outdent}""".stripMargin
  }
}

val a = Address("1 Scala Lane", "Anytown")
val p = Person("Buck Trends", a)

println(a.toJSON())
println()
println(p.toJSON())
