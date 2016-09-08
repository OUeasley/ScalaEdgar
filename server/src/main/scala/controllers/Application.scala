package controllers

import com.google.inject.Inject
import services.{ApiService, ScalaEdgar}
import java.nio.ByteBuffer

import akka.actor.ActorSystem
import boopickle.Default._
import com.google.inject.Inject
import play.api.{Configuration, Environment}
import play.api.mvc._
import spatutorial.shared.Api

import scala.concurrent.ExecutionContext.Implicits.global
import scala.reflect.macros.whitebox
import play.api._
import play.api.mvc._

object Router extends autowire.Server[ByteBuffer, Pickler, Pickler] {
	override def read[R: Pickler](p: ByteBuffer) = Unpickle[R].fromBytes(p)
	override def write[R: Pickler](r: R) = Pickle.intoBytes(r)
}

class Application @Inject() (implicit val config: Configuration, env: Environment, system: ActorSystem)  extends Controller {
	val apiService = new ApiService(system)

	def index = Action {
		Ok(views.html.index("SPA tutorial"))
	}

	def latestByCompany(company: String) = Action {
		val scalaEdgar = new ScalaEdgar()
		Ok(scalaEdgar.lastestByCompany(company))
	}

	def autowireApi(path: String) = Action.async(parse.raw) {
		implicit request =>
			println(s"Request path: $path")

			// get the request body as ByteString
			val b = request.body.asBytes(parse.UNLIMITED).get

			// call Autowire route
			Router.route[Api](apiService)(
				autowire.Core.Request(path.split("/"), Unpickle[Map[String, ByteBuffer]].fromBytes(b.asByteBuffer))
			).map(buffer => {
				val data = Array.ofDim[Byte](buffer.remaining())
				buffer.get(data)
				Ok(data)
			})
	}

	def logging = Action(parse.anyContent) {
		implicit request =>
			request.body.asJson.foreach { msg =>
				println(s"CLIENT - $msg")
			}
			Ok("")
	}

}