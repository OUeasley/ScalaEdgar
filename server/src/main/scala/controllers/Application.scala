package controllers

import services.ScalaEdgar

import scala.reflect.macros.whitebox
import play.api._
import play.api.mvc._

class Application extends Controller {

	def index = Action {
		Ok("yay")
	}

	def latestByCompany(company: String) = Action {
		val scalaEdgar = new ScalaEdgar()
		Ok(scalaEdgar.lastestByCompany(company))
	}

}