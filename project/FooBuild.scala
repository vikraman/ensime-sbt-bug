import sbt._
import Keys._

object FooBuild extends Build {

  lazy val standardSettings = Defaults.defaultSettings

  lazy val foo: Project = Project(
    id = "foo",
    base = file("."))
    .settings(standardSettings)
    .aggregate(macros, core)

  lazy val macros: Project = Project(
    id = "macros",
    base = file("macros"))
    .settings(standardSettings)

  lazy val core: Project = Project(
    id = "core",
    base = file("core")
  ).settings(standardSettings)
    .dependsOn(macros)

}
