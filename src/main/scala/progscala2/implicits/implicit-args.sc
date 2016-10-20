// src/main/scala/progscala2/implicits/implicit-args.sc

// Never use Floats for money:
def calcTax(amount: Float)(implicit rate: Float): Float = amount * rate

object SimpleStateSalesTax {
  implicit val rate: Float = 0.05F
}

case class ComplicatedSalesTaxData(
  baseRate: Float,
  isTaxHoliday: Boolean,
  storeId: Int)

object ComplicatedSalesTax {
  private def extraTaxRateForStore(id: Int): Float = {
    // From id, determine location, then extra taxes...
    0.0F
  }

  implicit def rate(implicit cstd: ComplicatedSalesTaxData): Float = {
    println("[ComplicatedSalesTax::rate] BEGIN")
    println(s"[ComplicatedSalesTax::rate] cstd.baseRate : ${cstd.baseRate}")
    if (cstd.isTaxHoliday) 0.0F
    else cstd.baseRate + extraTaxRateForStore(cstd.storeId)
  }
}

{
  import SimpleStateSalesTax.rate

  val amount = 100F
  println(s"Tax on $amount = ${calcTax(amount)}")
}

{
  import ComplicatedSalesTax.rate
  implicit val myStore = ComplicatedSalesTaxData(0.06F, false, 1010)

  val amount = 100F
  println(s"Tax on $amount = ${calcTax(amount)}")
}

/**
  * Cristiano:implicit
  * It doesn’t matter that we call calcTax inside an interpolated string.
  * The implicit values are still used for the rate argument.
  * For the “complicated” case, we use an implicit method, which itself
  * takes an implicit argument with the data it needs.
  */