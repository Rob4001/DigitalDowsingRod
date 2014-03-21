name := "DDR"

version := "1.0-SNAPSHOT"

libraryDependencies ++= Seq(
  javaJdbc,
  javaEbean,
  cache
)     

val appDependencies = Seq(
    "mysql" % "mysql-connector-java" % "5.1.18"
)

play.Project.defaultJavaSettings

val main = play.Project(appName, appVersion, appDependencies)
.settings(
    closureCompilerOptions += "ecmascript5"  
)
