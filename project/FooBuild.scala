import sbt._
import Keys._

import com.typesafe.sbt.SbtScalariform._
import scalariform.formatter.preferences._
import sbtunidoc.Plugin._, UnidocKeys._

object FooBuild extends Build {

  override lazy val settings = super.settings ++ Seq(
    organization := "foo.bar",
    version := "1.0-SNAPSHOT",

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
    )
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

  lazy val foo: Project = Project(
    id = "foo",
    base = file("."))
    .settings(unidocSettings)
    .aggregate(macros, core)

  lazy val macros: Project = Project(
    id = "macros",
    base = file("macros"))

  lazy val core: Project = Project(
    id = "core",
    base = file("core"))
    .settings(formatSettings)
    .dependsOn(macros)

}
