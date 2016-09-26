package services

import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import spatutorial.shared.{Filing, Filing10K, Filing10Q}

import scala.concurrent.duration._
import scala.concurrent.{Await, Future}
import scala.xml.{NodeSeq, XML}

/**
  * Created by hayleysmith on 8/31/16.
  */
class ScalaEdgar {
  implicit val system = ActorSystem()
  implicit val materializer = ActorMaterializer()
  implicit val executionContext = system.dispatcher

  def translateFilings(seq: NodeSeq): Unit = {
    (seq \\ "entry").map(node => {
      val date = node \\ "filing-date" text
      val link = node \\ "filing-href" text
      val filingType = node \\ "filing-type" text
      val name = node \\ "conformed-name" text
      val filing = filingType match {
        case "10-Q" => Filing10Q
        case "10-K" => Filing10K
      }
      Filing(name, date, filing,link)
    })
  }

  def lastestByCompany(ticker: String): NodeSeq = {
    val scalaEdgarConfiguration = ScalaEdgarConfiguration(ticker = "AAPL")
    parseXML(scalaEdgarConfiguration)
  }

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
}

/**
  * Created by Easle on 8/15/2016.
  */
object ScalaEdgar {
  val gaapXML = XML.load(getClass.getResource("/us-gaap-2016-01-31.xsd"))
}