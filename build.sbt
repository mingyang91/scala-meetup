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

Compile / mainClass := Some("meetup.compare.zio.request")

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

val circeVersion = "0.13.0"
lazy val circeFamily = Seq(
  "io.circe" %% "circe-core",
  "io.circe" %% "circe-parser",
  "io.circe" %% "circe-optics",
  "io.circe" %% "circe-generic-extras"
).map(_ % circeVersion)

libraryDependencies ++= circeFamily
libraryDependencies += "org.scala-lang.modules" %% "scala-parser-combinators" % "1.1.2"
libraryDependencies += "org.postgresql"          % "postgresql"               % "42.2.2"

// https://mvnrepository.com/artifact/mysql/mysql-connector-java
libraryDependencies += "mysql" % "mysql-connector-java" % "8.0.16"

// https://mvnrepository.com/artifact/software.amazon.awssdk/s3
libraryDependencies += "software.amazon.awssdk" % "s3" % "2.5.37"

libraryDependencies += "org.scala-lang.modules"     %% "scala-java8-compat"                % "0.9.0"
libraryDependencies += "org.typelevel"              %% "cats-core"                         % "2.1.1"
libraryDependencies += "com.lightbend.akka"         %% "akka-stream-alpakka-elasticsearch" % "1.1.2"
libraryDependencies += "com.lightbend.akka"         %% "akka-stream-alpakka-slick"         % "1.1.2"
libraryDependencies += "com.lightbend.akka"         %% "akka-stream-alpakka-s3"            % "1.1.2"
libraryDependencies += "com.typesafe.scala-logging" %% "scala-logging"                     % "3.9.2"
libraryDependencies ++= Seq(
  "ch.qos.logback" % "logback-classic" % "1.2.3"
)

val AkkaVersion       = "2.6.3"
val ZioVersion        = "1.0.1"
val ZioConfigVersion  = "1.0.0-RC24"
val ZioLoggingVersion = "0.3.2"

libraryDependencies ++= Seq(
  "org.rogach"         %% "scallop"             % "3.4.0",
  "com.typesafe.slick" %% "slick"               % "3.3.2",
  "org.slf4j"           % "slf4j-nop"           % "1.6.4",
  "com.typesafe.slick" %% "slick-hikaricp"      % "3.3.2",
  "com.chuusai"        %% "shapeless"           % "2.3.3",
  "dev.zio"            %% "zio"                 % ZioVersion,
  "dev.zio"            %% "zio-config"          % ZioConfigVersion,
  "dev.zio"            %% "zio-config-typesafe" % ZioConfigVersion,
  "dev.zio"            %% "zio-logging"         % ZioLoggingVersion,
  "dev.zio"            %% "zio-logging-slf4j"   % ZioLoggingVersion,
  "dev.zio"            %% "zio-nio"             % "1.0.0-RC9",
  "com.typesafe.slick" %% "slick"               % "3.3.1",
  "org.slf4j"           % "slf4j-nop"           % "1.7.26",
  "com.typesafe.slick" %% "slick-hikaricp"      % "3.3.1",
  "org.scalameta"      %% "svm-subs"            % "20.2.0"
)
libraryDependencies += "org.openjdk.jmh" % "jmh-generator-annprocess" % "1.23"

Global / onChangedBuildSource := ReloadOnSourceChanges
scalafmtOnCompile             := true
