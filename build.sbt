name := "DDR"

version := "1.0-SNAPSHOT"

libraryDependencies ++= Seq(
  javaJdbc,
  javaEbean,
  cache,
  "mysql" % "mysql-connector-java" % "5.1.18"
)     

play.Project.playJavaSettings




javascriptEntryPoints <<= baseDirectory(base =>
    base / "app" / "assets" / "javascripts" / "main" ** "*.js"
)

closureCompilerOptions += "ecmascript5"



