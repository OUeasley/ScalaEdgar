package controllers

import play.api._
import play.api.mvc._
import services.ScalaEdgar
import io.swagger.annotations._

class Application extends Controller {

	def index = Action {
		Ok(views.html.index("Your new application is ready."))
	}

	@Api(value = "/pet", description = "Operations about pets")
	@ApiOperation(nickname = "getPetById", value = "Find pet by ID", notes = "Returns a pet", response = classOf[models.EdgarFeed], httpMethod = "GET")
	@ApiResponses(Array(
		new ApiResponse(code = 400, message = "Invalid ID supplied"),
		new ApiResponse(code = 404, message = "Pet not found")))
	def latestByCompany(@ApiParam(value = "ID of the pet to fetch") company: String) = Action {
		val scalaEdgar = new ScalaEdgar()
		Ok(scalaEdgar.lastestByCompany(company))
	}

}