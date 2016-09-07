// src/main/scala/progscala2/dsls/payroll/common.scala
package progscala2.dsls.payroll

object common {
  sealed trait Amount { def amount: Double }                         // <1> Trait encapsulates a deduction amount

  case class Percentage(amount: Double) extends Amount {
    override def toString = s"$amount%"
  }

  case class Dollars(amount: Double) extends Amount {
    override def toString = s"$$$amount"
  }

  implicit class Units(amount: Double) {                             // <2> implicit class : it handles conversion from
    def percent = Percentage(amount)                                 //     Double to $ or % subtype (Used only in internal DSL)
    def dollars = Dollars(amount)
  }

  case class Deduction(name: String, amount: Amount) {               // <3> A type for a single deduction.
    override def toString = s"$name: $amount"
  }

  case class Deductions(                                             // <4> A type for all deductions.
    name: String,                                                    //     It also contains a name (biweekly) and a divisor
    divisorFromAnnualPay: Double = 1.0,                              //     to calculate a period's gross pay
    var deductions: Vector[Deduction] = Vector.empty) {

    def gross(annualSalary: Double): Double =                        // <5>
      annualSalary / divisorFromAnnualPay
    
    def net(annualSalary: Double): Double = {
      val g = gross(annualSalary)
      (deductions foldLeft g) { 
        case (total, Deduction(deduction, amount)) => amount match {
          case Percentage(value) => total - (g * value / 100.0)
          case Dollars(value) => total - value
        }
      }
    }

    override def toString =                                          // <6>
      s"$name Deductions:" + deductions.mkString("\n  ", "\n  ", "")
  }
}
