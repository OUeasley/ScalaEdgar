package spatutorial.client

import japgolly.scalajs.react.ReactDOM
import japgolly.scalajs.react.extra.router._
import japgolly.scalajs.react.vdom.prefix_<^._
import org.scalajs.dom
import spatutorial.client.components.GlobalStyles
import spatutorial.client.logger._
import spatutorial.client.modules._
import spatutorial.client.services.SPACircuit

import scala.scalajs.js
import scala.scalajs.js.annotation.JSExport
import scalacss.Defaults._
import scalacss.ScalaCssReact._

@JSExport("SPAMain")
object SPAMain extends js.JSApp {

  // Define the locations (pages) used in this application
  sealed trait Loc

  case object DashboardLoc extends Loc

  case object TodoLoc extends Loc

  case object RecentFilingsLoc extends Loc

  // configure the router
  val routerConfig = RouterConfigDsl[Loc].buildConfig { dsl =>
    import dsl._

    val filingWrapper = SPACircuit.connect(_.filings)
    // wrap/connect components to the circuit
    (staticRoute(root, DashboardLoc) ~> renderR(ctl => SPACircuit.wrap(_.motd)(proxy => Dashboard(ctl, proxy)))
      | staticRoute("#recent", RecentFilingsLoc) ~> renderR(ctl => filingWrapper(Todo(_)))
      ).notFound(redirectToPage(DashboardLoc)(Redirect.Replace))
  }.renderWith(layout)

  val todoCountWrapper = SPACircuit.connect(_.todos.map(_.items.count(!_.completed)).toOption)
  // base layout for all pages
  def layout(c: RouterCtl[Loc], r: Resolution[Loc]) = {
    <.div(
      // here we use plain Bootstrap class names as these are specific to the top level layout defined here
      <.nav(^.className := "navbar navbar-dark bg-inverse navbar-fixed-top",
        <.button(^.className:="navbar-toggler hidden-sm-up", ^.`type`:="button", "data-toggle".reactAttr :="collapse","data-target".reactAttr:="#mainCollapse", "aria-controls".reactAttr := "mainCollapse", "aria-expanded".reactAttr := "false", "aria-label".reactAttr := "Toggle navigation"),
        <.div(^.className := "container",
          <.div(^.className := "navbar-header", <.span(^.className := "navbar-brand", "SEC Company Filings")),
          <.div(^.className := "collapse navbar-toggleable-xs", ^.id := "mainCollapse",
            // connect menu to model, because it needs to update when the number of open todos changes
            todoCountWrapper(proxy => MainMenu(c, r.page, proxy)),
            <.form(^.className := "form-inline pull-xs-right",
              <.input(^.className := "form-control", ^.`type` := "text", ^.placeholder := "Ticker"),
              <.button(^.className := "btn btn-outline-success", ^.`type` := "submit", "Search")
            )
          )
        )
      ),
      // currently active module is shown in this container
      <.div(^.className := "container", r.render())
    )
  }

  @JSExport
  def main(): Unit = {
    log.warn("Application starting")
    // send log messages also to the server
    log.enableServerLogging("/logging")
    log.info("This message goes to server as well")

    // create stylesheet
    GlobalStyles.addToDocument()
    // create the router
    val router = Router(BaseUrl.until_#, routerConfig)
    // tell React to render the router in the document body
    ReactDOM.render(router(), dom.document.getElementById("root"))
  }
}
