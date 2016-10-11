package actor

import akka.actor.{Actor, Props}
import akka.actor.Actor.Receive
import services.ScalaEdgar

import scala.xml.NodeSeq

/**
  * Created by beasley on 9/8/2016.
  */
object EdgarActor {
  def props = Props[EdgarActor]

  case class getLatestCompanyFilings(ticker : String)
  case class translateFilings(filings: NodeSeq)
}

class EdgarActor extends Actor {
  import EdgarActor._

  def receive = {
    case getLatestCompanyFilings(ticker : String) => {
      val scalaEdgar = new ScalaEdgar()
      sender() ! scalaEdgar.lastestByCompany(ticker)
    }
    case translateFilings(filings : NodeSeq) => {
      val scalaEdgar = new ScalaEdgar()
      sender() ! scalaEdgar.translateFilings(filings)
    }
  }
}
