//import akka.actor.ActorSystem
//import akka.stream.ActorMaterializer
//import services.ScalaEdgarConfiguration
//import transformers.ScalaEdgarTransformer
//
//import scala.concurrent.duration._
//import scala.concurrent.{Await, Future}
//import scala.xml.{Node, NodeSeq, XML}
//
//
//
//
//
//object Test extends App {
////	val scalaEdgar = new ScalaEdgar
////	val scalaEdgarConfiguration = ScalaEdgarConfiguration(ticker = "AAPL", formType = Some("10-Q"), count = 1)
////	val blah = scalaEdgar.parseXML(scalaEdgarConfiguration)
////	//list of docs
////	//doc
////	//docs have a filing date
////	//docs
////
////	val info = ScalaEdgarTransformer.egdarFeedFromXml(blah)
////	val docs = info.entries.flatMap(entry => {
////		XML.load("https://www.sec.gov/" + entry.getDocument)
////	})
////
////	docs.foreach(n => {
////		n.child.map(performNodeAction)
////	})
////
////	def performNodeAction(node: Node): Unit = {
////		if (node.child.length > 0) {
////			node.child.map(performNodeAction)
////		}
////		if (!node.label.equalsIgnoreCase("#PCDATA") && !node.text.contains("div")) {
////			println("label : " + node.label)
////			println("value : " + node.text)
////			println("attr : { ")
////			node.attributes.map(println)
////			println("attr : } ")
////		}
////	}
//}
//
