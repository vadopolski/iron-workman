package com.ironworkman

import com.softwaremill.sttp.{Id, Response}
import com.softwaremill.sttp.quick._
import slinky.core._
import slinky.core.annotations.react
import slinky.web.html._

import scala.scalajs.js
import scala.scalajs.js.annotation.{JSImport, ScalaJSDefined}
import com.softwaremill.sttp._
import com.softwaremill.sttp.akkahttp._
import com.softwaremill.sttp.json4s._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

@JSImport("resources/App.css", JSImport.Default)
@js.native
object AppCSS extends js.Object

@JSImport("resources/logo.svg", JSImport.Default)
@js.native
object ReactLogo extends js.Object

case class SprintItem(id: Long,
                      intervalAmount: Long,
                      intervalDuration: Long,
                      user: UserItem)

case class UserItem(id: Long,
                    userName: String)

@react class App extends Component {
  type Props = Unit
  case class State(items: Seq[SprintItem], text: String)
  case class HttpBinResponse(origin: String, headers: Map[String, String])
//  implicit val serialization = org.json4s.native.Serialization

  override def initialState = {
//    val value: Id[Response[String]] = sttp.get(uri"http://httpbin.org/ip").send()
//

//    val request = sttp
//      .get(uri"https://httpbin.org/get")
//      .response(asJson[HttpBinResponse])
//
//    implicit val backend = AkkaHttpBackend()
//    val response: Future[Response[HttpBinResponse]] = request.send()
//
//    for {
//      r <- response
//    } {
//      println(s"Got response code: ${r.code}")
//      println(r.body)
//      backend.close()
//    }

    val test: Long = 777

    State(
      Seq(
        SprintItem(0, test, test, UserItem(1, "Vadim")),
        SprintItem(1, 6, 30, UserItem(1, "Vadim"))
      ),
      ""
    )
  }

  private val css = AppCSS

  def render() = {
    div(className := "App")(
      header(className := "App-header")(
        img(
          src := ReactLogo.asInstanceOf[String],
          className := "App-logo",
          alt := "logo"
        ),
        h1(className := "App-title")("Welcome to Iron Workman App")
      ),
      p(className := "App-intro")(
        "To get started, edit ",
        code("App.scala"),
        " and save to reload."
      ),
      div(h3("SPRINT LIST"), SprintList(items = state.items))
    )
  }
}

@react class SprintList extends StatelessComponent {
  case class Props(items: Seq[SprintItem])

  override def render() = {
    ul(props.items.map { item =>
      li(key := item.id.toString)(item.intervalAmount.toString)
    })
  }
}
