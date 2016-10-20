/**
  * Cristiano:implicit
  * Scenarios for Implicit Arguments
  * The first category is boilerplate elimination,
  * such as providing context information implicitly rather than explicitly.
  *
  * The second category includes constraints that reduce bugs or limit the
  * allowed types that can be used with certain methods with parameterized types.
  *
  * execution context :
  * Passing an “execution context” is one recommended use of implicit arguments.
  * Other example contexts include transactions, database connections, thread pools,
  * and user sessions.
  *
  * capabilities:
  * Besides passing contexts, implicit arguments can be used to control capabilities.
  * For example, an implicit user session argument might contain authorization
  * tokens that control whether or not certain API operations can be invoked
  * on behalf of the user or to limit data visibility.
  * Suppose you are constructing a menu for a user interface and some menu items
  * are shown only if the user is logged in, while others are shown only if the user
  * isn’t logged in:
  */
/*
def createMenu(implicit session: Session): Menu = {
  val defaultItems = List(helpItem, searchItem)

  val accountItems =
    if (session.loggedin())
      List(viewAccountItem, editAccountItem)
    else List(loginItem)

  Menu(defaultItems ++ accountItems)
}
*/
