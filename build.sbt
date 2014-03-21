name := "DDR"

version := "1.0-SNAPSHOT"

libraryDependencies ++= Seq(
  javaJdbc,
  javaEbean,
  cache
)     

play.Project.playJavaSettings

val appDependencies = Seq(
    "mysql" % "mysql-connector-java" % "5.1.18"
)

javascriptEntryPoints <<= baseDirectory(base =>
    base / "app" / "assets" / "javascripts" / "main" ** "*.js"
)

closureCompilerOptions += "ecmascript5"



