// src/main/scala/progscala2/patternmatching/http.sc

/**
  * Cristiano:Sealed Hierarchies and Exhaustive Matches
  *
  * Tip :When pattern matching on an instance of a sealed base class,
  * the match is exhaustive if the case clauses cover all the
  * derived types defined in the same source file.
  * Because no user-defined derived types are allowed,
  * the match can never become nonexhaustive as the project evolves,
  * since users are prevented from defining new types.
  *
  *
  * Tip:An abstract, no-argument method declaration in a parent type
  * can be implemented by a val in a subtype.
  * A recommended practice is to declare abstract, no-argument methods
  * instead of vals in abstract parent types,
  * leaving subtype implementers greater freedom to implement
  * the member with either a method or a val.
  *
  */

sealed abstract class HttpMethod() {                                 // <1>
    def body: String                                                 // <2>
    def bodyLength = body.length
}

case class Connect(body: String) extends HttpMethod                  // <3>
case class Delete (body: String) extends HttpMethod
case class Get    (body: String) extends HttpMethod
case class Head   (body: String) extends HttpMethod
case class Options(body: String) extends HttpMethod
case class Post   (body: String) extends HttpMethod
case class Put    (body: String) extends HttpMethod
case class Trace  (body: String) extends HttpMethod

def handle (method: HttpMethod) = method match {                     // <4>
  case Connect (body) => s"connect: (length: ${method.bodyLength}) $body"
  case Delete  (body) => s"delete:  (length: ${method.bodyLength}) $body"
  case Get     (body) => s"get:     (length: ${method.bodyLength}) $body"
  case Head    (body) => s"head:    (length: ${method.bodyLength}) $body"
  case Options (body) => s"options: (length: ${method.bodyLength}) $body"
  case Post    (body) => s"post:    (length: ${method.bodyLength}) $body"
  case Put     (body) => s"put:     (length: ${method.bodyLength}) $body"
  case Trace   (body) => s"trace:   (length: ${method.bodyLength}) $body"
}

val methods = Seq(
  Connect("connect body..."),
  Delete ("delete body..."),
  Get    ("get body..."),
  Head   ("head body..."),
  Options("options body..."),
  Post   ("post body..."),
  Put    ("put body..."),
  Trace  ("trace body..."))

methods foreach (method => println(handle(method)))
