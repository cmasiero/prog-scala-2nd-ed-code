// src/main/scala/progscala2/typelessdomore/shapes/shapes.scala
package progscala2.typelessdomore.shapes

//import com.sun.javafx.sg.prism.RegionImageCache.CachedImage

case class Point(x: Double = 0.0, y: Double = 0.0) {
  /*
   * Cristiano:Method Default and Named Arguments
   */
  def shift(deltax: Double = 0.0, deltay: Double = 0.0) =
    copy (x + deltax, y + deltay)
}

abstract class Shape() {
  /**
    * Cristiano:Methods with Multiple Argument Lists
    * Draw takes TWO argument LISTS, one list with an offset for drawing,
    * and the other list that is the function argument we used previously. (???)
    *
    * You can have as many argument lists as you want, but it’s rare to use more than two.
    *
    */
  def draw(offset: Point = Point(0.0, 0.0))(f: String => Unit): Unit =
    f(s"draw(offset = $offset), ${this.toString}")

  /**
    * Cristiano:Methods with Multiple Argument Lists
    * Multiple lists promote a very nice block-structure syntax when the last argument list
    * takes a single function.
    * Here’s how we might invoke this new draw method:
    * s.draw(Point( 1.0, 2.0))(
    * str => println( s" ShapesDrawingActor: $str"))
    *
    * Scala lets us replace parentheses with curly braces around an argument list.
    * So, this line can also be written this way:
    * s.draw( Point( 1.0, 2.0)){
    * str => println( s" ShapesDrawingActor: $str")}
    *
    * Or equivalently:
    * s.draw(Point( 1.0, 2.0)) { str => println( s" ShapesDrawingActor: $str") }
    *
    * {…} block is still a function we’re passing to draw.
    *
    * If we use the default offset, the first set of parentheses is still required:
    * s.draw() {
    * str => println( s" ShapesDrawingActor: $str")
    * }
    *
   */
}

case class Circle(center: Point, radius: Double) extends Shape

case class Rectangle(lowerLeft: Point, height: Double, width: Double)
  extends Shape

/**
  * Cristiano:Methods with Multiple Argument Lists
  * My test.
  */
object Test {
  def main(args: Array[String]): Unit = {

    val c = Circle (Point(0.0, 0.0),10.0)
    c.draw(){
      str => println( s" ShapesDrawingActor: $str")
    }

  }
}