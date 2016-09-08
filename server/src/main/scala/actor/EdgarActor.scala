package actor

import akka.actor.{Actor, Props}
import akka.actor.Actor.Receive
import services.ScalaEdgar

/**
  * Created by beasley on 9/8/2016.
  */
object EdgarActor {
  def props = Props[EdgarActor]

  case class getLatestCompanyFilings(ticker : String)
}

class EdgarActor extends Actor {
  import EdgarActor._

  def receive = {
    case getLatestCompanyFilings(ticker : String) => {
      val scalaEdgar = new ScalaEdgar()
      sender() ! scalaEdgar.lastestByCompany(ticker)
    }
  }
}
