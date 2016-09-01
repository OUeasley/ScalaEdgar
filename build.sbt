name := "ScalaEdgar"

version := "1.0"

lazy val `scalaedgar` = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.8"

lazy val akkaVersion = "2.4.9"

libraryDependencies ++= Seq( jdbc , cache , ws   , specs2 % Test )

unmanagedResourceDirectories in Test <+=  baseDirectory ( _ /"target/web/public/test" )  

resolvers += "scalaz-bintray" at "https://dl.bintray.com/scalaz/releases"

libraryDependencies ++= Seq(
	"com.typesafe.akka" %% "akka-actor" % akkaVersion,
	"com.typesafe.akka" %% "akka-agent" % akkaVersion,
	"com.typesafe.akka" %% "akka-camel" % akkaVersion,
	"com.typesafe.akka" %% "akka-cluster" % akkaVersion,
	"com.typesafe.akka" %% "akka-cluster-metrics" % akkaVersion,
	"com.typesafe.akka" %% "akka-cluster-sharding" % akkaVersion,
	"com.typesafe.akka" %% "akka-cluster-tools" % akkaVersion,
	"com.typesafe.akka" %% "akka-contrib" % akkaVersion,
	"com.typesafe.akka" %% "akka-http-core" % akkaVersion,
	"com.typesafe.akka" %% "akka-http-testkit" % akkaVersion,
	"com.typesafe.akka" %% "akka-multi-node-testkit" % akkaVersion,
	"com.typesafe.akka" %% "akka-osgi" % akkaVersion,
	"com.typesafe.akka" %% "akka-persistence" % akkaVersion,
	"com.typesafe.akka" %% "akka-persistence-tck" % akkaVersion,
	"com.typesafe.akka" %% "akka-remote" % akkaVersion,
	"com.typesafe.akka" %% "akka-slf4j" % akkaVersion,
	"com.typesafe.akka" %% "akka-stream" % akkaVersion,
	"com.typesafe.akka" %% "akka-stream-testkit" % akkaVersion,
	"com.typesafe.akka" %% "akka-testkit" % akkaVersion,
	"com.typesafe.akka" %% "akka-distributed-data-experimental" % akkaVersion,
	"com.typesafe.akka" %% "akka-typed-experimental" % akkaVersion,
	"com.typesafe.akka" %% "akka-http-experimental" % akkaVersion,
	"com.typesafe.akka" %% "akka-http-jackson-experimental" % akkaVersion,
	"com.typesafe.akka" %% "akka-http-spray-json-experimental" % akkaVersion,
	"com.typesafe.akka" %% "akka-http-xml-experimental" % akkaVersion,
	"com.typesafe.akka" %% "akka-persistence-query-experimental" % akkaVersion,
	"org.scalactic" %% "scalactic" % "3.0.0",
	"org.scalatest" %% "scalatest" % "3.0.0" % "test",
	"com.netaporter" %% "scala-uri" % "0.4.14",
	"org.scalatestplus.play" %% "scalatestplus-play" % "1.5.1" % Test,
	"io.swagger" %% "swagger-play2" % "1.5.1"
)

libraryDependencies += "org.scalaxb" % "scalaxb-maven-plugin" % "1.4.1"

libraryDependencies += "net.ruippeixotog" %% "scala-scraper" % "1.0.0"
