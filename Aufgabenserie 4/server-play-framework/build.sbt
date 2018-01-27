name := "bulletin-board"
organization := "de.vs"

version := "1.0-SNAPSHOT"

lazy val myProject = (project in file("."))
  .enablePlugins(PlayJava, PlayEbean)

scalaVersion := "2.12.2"

libraryDependencies += guice
libraryDependencies += "javax.xml.bind" % "jaxb-api" % "2.3.0"

// Database Injection
libraryDependencies +=  jdbc
libraryDependencies += "org.xerial" % "sqlite-jdbc" % "3.8.6"
































































































