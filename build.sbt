enablePlugins(ScalaJSPlugin)
import sbt.Keys._

val projectName = "scalajs-boilerplate"
val libVersion     = "0.1-SNAPSHOT"

lazy val root = project.in(file(".")).
  aggregate(boilerplateJS, boilerplateJVM).
  settings(
    publish := {},
    publishLocal := {}
  )

lazy val boilerplate = crossProject.in(file(".")).
  settings(
    name := projectName,
    version := "0.1-SNAPSHOT",
    organization := "org.me",
    scalaVersion := "2.11.6",
    libraryDependencies ++= Seq(
    )
  ).
  jvmSettings(
  ).
  jsSettings(
    persistLauncher in Compile := true,
    persistLauncher in Test := false,
    jsDependencies ++= Seq(
    ),
    libraryDependencies ++= {
      Seq(
      )
    }
  )

lazy val shared = Project(s"$projectName-shared", file("shared")).
  settings(
    scalaVersion := "2.11.6",
    libraryDependencies ++= sharedLibs.value,
    libraryDependencies ++= Seq(
    ))

lazy val sharedLibs = Def.setting(Seq())


lazy val boilerplateJVM = boilerplate.jvm
lazy val boilerplateJS = boilerplate.js
