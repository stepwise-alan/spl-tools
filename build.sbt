import sbt.Keys.libraryDependencies
import sbt.project

ThisBuild / version := "0.1.0-SNAPSHOT"

// ThisBuild / scalaVersion := "3.1.3"

ThisBuild / scalaVersion := "2.12"

lazy val root = (project in file("."))
  .settings(
    name := "spl-tools",
    libraryDependencies ++= Seq(
      // "de.fosd.typechef" %% "frontend" % "0.4.2" cross CrossVersion.for3Use2_13,
      "de.fosd.typechef" %% "frontend" % "0.4.1" cross CrossVersion.for3Use2_13,
      // "de.fosd.typechef" %% "frontend" % "0.3.7",
      "org.bytedeco.javacpp-presets" % "llvm" % "7.0.1-1.4.4",
      "com.regblanc" %% "scala-smtlib" % "0.2.1-42-gc68dbaa" cross CrossVersion.for3Use2_13,
      "com.opencsv" % "opencsv" % "5.7.1",
    )
  )
