enablePlugins(JavaAppPackaging, GraalVMNativeImagePlugin)

val buildTimePkg = Seq(
//  "scala.runtime.Statics$VM",
  "org.slf4j",
  "ch.qos.logback"
).mkString(",")

val runTimePkg = Seq(
  "io.netty.util.internal.logging.Log4JLogger",
  "scala.util.Random$"
//  "akka.protobuf.DescriptorProtos",
//  "com.typesafe.config.impl.ConfigImpl$EnvVariablesHolder",
//  "com.typesafe.config.impl.ConfigImpl$SystemPropertiesHolder"
).mkString(",")

// Compile / mainClass := Some("meetup.compare.zio.request")

graalVMNativeImageOptions := Seq(
  "-H:IncludeResources=^([^.]+\\.(conf|csv|properties|sql|routes|xml))$",
  "-H:+TraceClassInitialization",
  "-H:+ReportExceptionStackTraces",
  "--no-server",
  "--no-fallback",
  "--allow-incomplete-classpath",
//  "--report-unsupported-elements-at-runtime",
  "--initialize-at-build-time", // + buildTimePkg,
  "--initialize-at-run-time=" + runTimePkg
)

name := "scala-playground"

maintainer := "mingyang@nadileaf.com"

organization := "Mesoor .Inc"

scalaVersion := scalaV.v213

libraryDependencies += "org.scala-lang.modules" %% "scala-parser-combinators" % "1.1.2"

// https://mvnrepository.com/artifact/software.amazon.awssdk/s3
libraryDependencies += "software.amazon.awssdk" % "s3" % "2.5.37"

libraryDependencies ++= libScalax.`circe`.value
libraryDependencies ++= libScalax.`postgresql-jdbc`.value
libraryDependencies ++= libScalax.`mysql-connector-java`.value
libraryDependencies ++= libScalax.`scala-java8-compat`.value
libraryDependencies ++= libScalax.`cats`.value
libraryDependencies ++= libScalax.`logback-classic`.value
libraryDependencies ++= libScalax.`shapeless`.value
libraryDependencies += "com.lightbend.akka"         %% "akka-stream-alpakka-elasticsearch" % "1.1.2"
libraryDependencies += "com.lightbend.akka"         %% "akka-stream-alpakka-slick"         % "1.1.2"
libraryDependencies += "com.lightbend.akka"         %% "akka-stream-alpakka-s3"            % "1.1.2"
libraryDependencies += "com.typesafe.scala-logging" %% "scala-logging"                     % "3.9.2"

val AkkaVersion       = "2.6.3"
val ZioVersion        = "1.0.1"
val ZioConfigVersion  = "1.0.0-RC24"
val ZioLoggingVersion = "0.3.2"

libraryDependencies ++= Seq(
  "org.rogach"    %% "scallop"             % "3.4.0",
  "dev.zio"       %% "zio"                 % ZioVersion,
  "dev.zio"       %% "zio-config"          % ZioConfigVersion,
  "dev.zio"       %% "zio-config-typesafe" % ZioConfigVersion,
  "dev.zio"       %% "zio-logging"         % ZioLoggingVersion,
  "dev.zio"       %% "zio-logging-slf4j"   % ZioLoggingVersion,
  "dev.zio"       %% "zio-nio"             % "1.0.0-RC9",
  "org.slf4j"      % "slf4j-nop"           % "1.7.26",
  "org.scalameta" %% "svm-subs"            % "20.2.0"
)
libraryDependencies += "org.openjdk.jmh" % "jmh-generator-annprocess" % "1.23"

Global / onChangedBuildSource := ReloadOnSourceChanges
scalafmtOnCompile             := true
