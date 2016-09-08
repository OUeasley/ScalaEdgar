import sbt._
import org.scalajs.sbtplugin.ScalaJSPlugin.autoImport._

/**
	* Application settings. Configure the build for your application here.
	* You normally don't have to touch the actual build definition after this.
	*/
object Settings {
	/** The name of your application */
	val name = "scalajs-spa"

	/** The version of your application */
	val version = "1.1.4"

	/** Options for the scala compiler */
	val scalacOptions = Seq(
		"-Xlint",
		"-unchecked",
		"-deprecation",
		"-feature"
	)

	/** Declare global dependency versions here to avoid mismatches in multi part dependencies */
	object versions {
		val scala = "2.11.8"
		val scalaDom = "0.9.1"
		val scalajsReact = "0.11.1"
		val scalaCSS = "0.4.1"
		val log4js = "1.4.10"
		val autowire = "0.2.5"
		val booPickle = "1.2.4"
		val diode = "1.0.0"
		val uTest = "0.4.3"

		val react = "15.1.0"
		val jQuery = "1.11.1"
		val bootstrap = "3.3.6"
		val chartjs = "2.1.3"

		val playScripts = "0.5.0"
	}

	/**
		* These dependencies are shared between JS and JVM projects
		* the special %%% function selects the correct version for each project
		*/
	val sharedDependencies = Def.setting(Seq(
		"com.lihaoyi" %%% "autowire" % versions.autowire,
		"me.chrons" %%% "boopickle" % versions.booPickle
	))

	lazy val akkaVersion = "2.4.9"

	/** Dependencies only used by the JVM project */
	val jvmDependencies = Def.setting(Seq(
		"com.vmunier" %% "play-scalajs-scripts" % versions.playScripts,
		"org.webjars" % "font-awesome" % "4.3.0-1" % Provided,
		"org.webjars.bower" % "compass-mixins" % "0.12.7",
		"org.webjars" % "bootstrap" % "4.0.0-alpha.3",
		"com.lihaoyi" %% "utest" % versions.uTest % Test,
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
		"net.ruippeixotog" %% "scala-scraper" % "1.0.0",
		"org.scalatestplus.play" %% "scalatestplus-play" % "1.5.1" % Test
	))

	/** Dependencies only used by the JS project (note the use of %%% instead of %%) */
	val scalajsDependencies = Def.setting(Seq(
		"com.github.japgolly.scalajs-react" %%% "core" % versions.scalajsReact,
		"com.github.japgolly.scalajs-react" %%% "extra" % versions.scalajsReact,
		"com.github.japgolly.scalacss" %%% "ext-react" % versions.scalaCSS,
		"me.chrons" %%% "diode" % versions.diode,
		"me.chrons" %%% "diode-react" % versions.diode,
		"org.scala-js" %%% "scalajs-dom" % versions.scalaDom,
		"com.lihaoyi" %%% "utest" % versions.uTest % Test
	))

	/** Dependencies for external JS libs that are bundled into a single .js file according to dependency order */
	val jsDependencies = Def.setting(Seq(
		"org.webjars.bower" % "react" % versions.react / "react-with-addons.js" minified "react-with-addons.min.js" commonJSName "React",
		"org.webjars.bower" % "react" % versions.react / "react-dom.js" minified "react-dom.min.js" dependsOn "react-with-addons.js" commonJSName "ReactDOM",
		"org.webjars" % "jquery" % versions.jQuery / "jquery.js" minified "jquery.min.js",
		"org.webjars" % "bootstrap" % versions.bootstrap / "bootstrap.js" minified "bootstrap.min.js" dependsOn "jquery.js",
		"org.webjars" % "chartjs" % versions.chartjs / "Chart.js" minified "Chart.min.js",
		"org.webjars" % "log4javascript" % versions.log4js / "js/log4javascript_uncompressed.js" minified "js/log4javascript.js"
	))
}