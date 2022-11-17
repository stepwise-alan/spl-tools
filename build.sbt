import sbt.Keys.libraryDependencies
import sbt.project

ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "3.1.3"

lazy val root = (project in file("."))
  .settings(
    name := "spl-tools",
    libraryDependencies ++= Seq(
      "de.fosd.typechef" %% "frontend" % "0.4.2" cross CrossVersion.for3Use2_13,
      "com.opencsv" % "opencsv" % "5.6",
    )
  )
