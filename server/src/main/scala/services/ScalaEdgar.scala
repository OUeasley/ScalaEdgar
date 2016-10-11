package services

import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import transformers.ScalaEdgarTransformer

import scala.concurrent.duration._
import scala.concurrent.{Await, Future}
import scala.xml.{Node, NodeSeq, XML}

/**
	* Created by hayleysmith on 8/31/16.
	*/
class ScalaEdgar {
	implicit val system = ActorSystem()
	implicit val materializer = ActorMaterializer()
	implicit val executionContext = system.dispatcher

	def parseXML(scalaEdgarConfiguration: ScalaEdgarConfiguration): NodeSeq = {
		try {
			val getXml = Future {
				XML.load(constructUri(scalaEdgarConfiguration))
			}
			Await.result(getXml, 5 seconds)
		} catch {
			case e: Exception =>
				println("---------------------------------------------------")
				e.printStackTrace()
				throw new UnsupportedOperationException("Something went horribly wrong!")
		}
	}

	private def constructUri(conf: ScalaEdgarConfiguration): String = {
		import com.netaporter.uri.dsl._
		val p = ("CIK", conf.ticker) :: ("type", conf.formType) :: ("start", conf.start) :: ("owner", conf.owner) ::
			("output", conf.output) :: ("count", conf.count) :: Nil
		val uri = "https://www.sec.gov/cgi-bin/browse-edgar".addParams(p)
		uri.toString()
	}

	def lastestByCompany(ticker :String): NodeSeq = {
		val scalaEdgarConfiguration = ScalaEdgarConfiguration(ticker = "AAPL")
		parseXML(scalaEdgarConfiguration)
	}
}

/**
	* Created by Easle on 8/15/2016.
	*/
object ScalaEdgar {
	val gaapXML = XML.load(getClass.getResource("/us-gaap-2016-01-31.xsd"))
}