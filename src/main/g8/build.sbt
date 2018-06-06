/////////////////////////////////
// Defaults
/////////////////////////////////

name := "$nameProject$"

organization := "$organization$"

scalaVersion in ThisBuild := "$scalaVersion$"

val sparkVersion = "$sparkVersion$"



/////////////////////////////////
// Useful aliases
/////////////////////////////////

addCommandAlias("cd", "project") // navigate the projects

addCommandAlias("cc", ";clean;compile") // clean and compile

addCommandAlias("pl", ";clean;publishLocal") // clean and publish locally

addCommandAlias("pr", ";clean;publish") // clean and publish globally

addCommandAlias("pld", ";clean;local:publishLocal;docker:publishLocal") // clean and publish/launch the docker environment



/////////////////////////////////
// Formatting
/////////////////////////////////

scalafmtConfig in ThisBuild := Some(file(".scalafmt.conf")) // one config to rule them all


/////////////////////////////////
// Resolvers
/////////////////////////////////
resolvers in ThisBuild ++= Seq(
  "conjars.org" at "http://conjars.org/repo",
  "cakesolutions" at "http://dl.bintray.com/cakesolutions/maven/",
  "bintray-backline-open-source-releases" at "https://dl.bintray.com/backline/open-source",
  "lightshed-maven" at "http://dl.bintray.com/content/lightshed/maven",
  "confluent" at "http://packages.confluent.io/maven/",
  "sonatype-releases" at "https://oss.sonatype.org/content/repositories/releases/",
  "Typesafe repository" at "http://repo.typesafe.com/typesafe/releases/",
  "Second Typesafe repo" at "http://repo.typesafe.com/typesafe/maven-releases/",
  Resolver.sonatypeRepo("public")
)


/////////////////////////////////
// Dependencies
/////////////////////////////////
libraryDependencies in ThisBuild ++= Seq(
  "org.apache.spark"  %% "spark-core" % sparkVersion,
  "org.apache.spark" % "spark-sql_2.11" % sparkVersion,
  "org.apache.spark" %% "spark-hive" % sparkVersion,
  "org.apache.spark" %% "spark-streaming" % sparkVersion % "provided",
  "com.github.nscala-time" %% "nscala-time" % "2.18.0",
  "joda-time" % "joda-time" % "2.9.9",
  "com.holdenkarau" %% "spark-testing-base" % "$sparkVersion$_$sparkTestingbaseRelease$" % "test",
  "com.github.tomakehurst" % "wiremock"    % "2.13.0" % "test",
  "org.scalacheck"  %% "scalacheck" % "1.13.5" % Test,
  "org.scalactic" %% "scalactic" % "3.2.0-SNAP10",
  "org.scalatest" %% "scalatest" % "3.2.0-SNAP10" % "test",
  "com.typesafe.scala-logging" % "scala-logging_2.11" % "3.7.2",
  "org.scala-lang" % "scala-reflect" %"2.11.8",
  "com.fasterxml.jackson.core" % "jackson-databind" % "2.9.3",
  "com.fasterxml.jackson.module" % "jackson-module-scala_2.11" % "2.9.2",
  "com.github.kxbmap" % "configs_2.11" % "0.4.4",
  "com.typesafe.scala-logging" %% "scala-logging" % "3.7.2",
  "io.prometheus" % "simpleclient" % "0.1.0",
  "io.prometheus" % "simpleclient_pushgateway" % "0.1.0"
)


/////////////////////////////////
// Sub projects definitions
/////////////////////////////////

sources in (Compile,doc) := Seq.empty
publishArtifact in (Compile, packageDoc) := false

lazy val common = project.in(file("common"))

lazy val $projectModuleFmt$ = project.in(file("$projectModuleDir$")).
  dependsOn(common % "compile->compile;test->test").
  enablePlugins(JavaAppPackaging)


lazy val root = project.in(file(".")).aggregate(
  common,
  $projectModuleFmt$
)
