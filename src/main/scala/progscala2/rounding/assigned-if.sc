// src/main/scala/progscala2/rounding/assigned-if.sc


/**
  * Cristiano:if expression,
  * we can assign the result of an if expression, as shown here:
  */
val configFile = new java.io.File("somefile.txt")

val configFilePath = if (configFile.exists()) {
  configFile.getAbsolutePath()
} else {
  configFile.createNewFile()
  configFile.getAbsolutePath()
}
