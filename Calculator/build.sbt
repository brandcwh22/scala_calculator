name := "calculator"

version := "0.1"

scalaVersion := "2.13.6"

lazy val osName = System.getProperty("os.name") match {
  case n if n.startsWith("Linux") => "linux"
  case n if n.startsWith("Mac") => "mac"
  case n if n.startsWith("Windows") => "win"
  case _ => throw new Exception("Unknown platform!")
}

// Add JavaFX dependencies
lazy val javaFXModules = Seq("base", "controls", "fxml", "graphics", "media", "swing", "web")
libraryDependencies ++= javaFXModules.map( m=>
  "org.openjfx" % s"javafx-$m" % "15" classifier osName
)

libraryDependencies += "org.scalafx" %% "scalafx" % "16.0.0-R22"
//libraryDependencies += "org.openjfx" % "javafx-base" % "15.0.1"