package services

import java.util.{Date, UUID}
import javax.inject.{Inject, Singleton}

import actor.EdgarActor
import actor.EdgarActor.{getLatestCompanyFilings, translateFilings}
import akka.actor._
import akka.pattern._
import akka.util.Timeout
import spatutorial.shared._

import scala.concurrent.Await
import scala.concurrent.duration._
import scala.xml.NodeSeq

@Singleton
class ApiService @Inject()(system: ActorSystem) extends Api {
  val edgarActor = system.actorOf(EdgarActor.props)
  var todos = Seq(
    TodoItem("41424344-4546-4748-494a-4b4c4d4e4f50", 0x61626364, "Wear shirt that says “Life”. Hand out lemons on street corner.", TodoLow, completed = false),
    TodoItem("2", 0x61626364, "Make vanilla pudding. Put in mayo jar. Eat in public.", TodoNormal, completed = false),
    TodoItem("3", 0x61626364, "Walk away slowly from an explosion without looking back.", TodoHigh, completed = false),
    TodoItem("4", 0x61626364, "Sneeze in front of the pope. Get blessed.", TodoNormal, completed = true)
  )

  override def welcomeMsg(name: String): String =
    s"Welcome to SPA, $name! Time is now ${new Date}"

  override def getAllTodos(): Seq[TodoItem] = {
    // provide some fake Todos
    Thread.sleep(300)
    println(s"Sending ${todos.size} Todo items")
    todos
  }

  // update a Todo
  override def updateTodo(item: TodoItem): Seq[TodoItem] = {
    // TODO, update database etc :)
    if (todos.exists(_.id == item.id)) {
      todos = todos.collect {
        case i if i.id == item.id => item
        case i => i
      }
      println(s"Todo item was updated: $item")
    } else {
      // add a new item
      val newItem = item.copy(id = UUID.randomUUID().toString)
      todos :+= newItem
      println(s"Todo item was added: $newItem")
    }
    Thread.sleep(300)
    todos
  }

  // delete a Todo
  override def deleteTodo(itemId: String): Seq[TodoItem] = {
    println(s"Deleting item with id = $itemId")
    Thread.sleep(300)
    todos = todos.filterNot(_.id == itemId)
    todos
  }

  override def getLatestForCompany(company: String): Seq[Filing] = {
    implicit val timeout = Timeout.durationToTimeout(5.seconds)
    val filingFuture = edgarActor ? getLatestCompanyFilings(company)
    val filingResults = Await.result(filingFuture, 5 seconds).asInstanceOf[NodeSeq]
    val translatedFilingFuture = edgarActor ? translateFilings(filingResults)
    Await.result(translatedFilingFuture, 5 seconds).asInstanceOf[Seq[Filing]]
  }
}