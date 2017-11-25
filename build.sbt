enablePlugins(ScalaJSPlugin)
import sbt.Keys._

val projectName  = "scalajs-boilerplate"
val scalaV       = "2.12.2"
val org          = "org.boilerplate"

lazy val root = project.in(file(".")).
  aggregate(boilerplateJS, boilerplateJVM).
  settings()

val sharedSettings = Seq(
  scalaVersion := scalaV,
  version      := "0.1-SNAPSHOT",
  organization := org
)

lazy val sharedLibs = Seq()

lazy val boilerplate = crossProject.in(file("."))
  .settings(
    name := projectName,
    organization := org,
    scalaVersion := scalaV,
    libraryDependencies ++= sharedLibs ++ Seq() // Shared within cross-project
  ).
  jvmSettings(
    libraryDependencies ++= Seq() // JVM Only
  ).
  jsSettings(
    scalaJSUseMainModuleInitializer := true,
    jsDependencies ++= Seq(), // Web Jars
    libraryDependencies ++= Seq("org.scala-js" %%% "scalajs-dom" % "0.9.3") // JS Only
  )

lazy val shared = Project(s"$projectName-shared", file("shared"))
   .settings( sharedSettings ++ Seq(
        libraryDependencies ++= sharedLibs ++ Seq()
    )
   )


lazy val boilerplateJVM = boilerplate.jvm
lazy val boilerplateJS = boilerplate.js
