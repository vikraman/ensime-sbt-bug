import sbt._
import Keys._

import com.typesafe.sbt.SbtScalariform._
import scalariform.formatter.preferences._

object FooBuild extends Build {

  lazy val standardSettings = Defaults.defaultSettings ++ Seq(

    organization := "foo.bar",
    version := "1.0-SNAPSHOT",

    resolvers ++= Seq(
      Resolver.sonatypeRepo("public"),
      Resolver.sonatypeRepo("releases"),
      Resolver.sonatypeRepo("snapshots"),
      Resolver.typesafeRepo("releases"),
      Resolver.typesafeRepo("snapshots"),
      Resolver.sbtPluginRepo("releases"),
      Resolver.sbtPluginRepo("snapshots")
    ),

    scalaVersion := "2.11.7",

    scalacOptions ++= Seq(
      "-encoding", "UTF-8",
      "-deprecation",
      "-unchecked",
      "-feature",
      "-Xlint",
      "-optimise",
      "-target:jvm-1.8",
      "-Yinline-warnings:false",
      "-Xfatal-warnings",
      "-Ywarn-value-discard"
    ),

    javacOptions ++= Seq(
      "-source", "1.8",
      "-target", "1.8",
      "-Xlint"
    ),

    updateOptions := updateOptions.value.withCachedResolution(true)
  )

  lazy val formatSettings = scalariformSettings ++ Seq(
    ScalariformKeys.preferences := ScalariformKeys.preferences.value
      .setPreference(AlignArguments, true)
      .setPreference(AlignParameters, true)
      .setPreference(AlignSingleLineCaseStatements, true)
      .setPreference(DoubleIndentClassDeclaration, true)
      .setPreference(PlaceScaladocAsterisksBeneathSecondAsterisk, true)
      .setPreference(PreserveDanglingCloseParenthesis, true)
  )

  lazy val defaultSettings = standardSettings

  lazy val foo: Project = Project(
    id = "foo",
    base = file("."))
    .settings(defaultSettings)
    .aggregate(macros, core)

  lazy val macros: Project = Project(
    id = "macros",
    base = file("macros"))
  .settings(defaultSettings)

  lazy val core: Project = Project(
    id = "core",
    base = file("core")
  ).settings(defaultSettings)
  .dependsOn(macros)

}
