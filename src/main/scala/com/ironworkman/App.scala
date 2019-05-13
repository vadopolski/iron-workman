package com.ironworkman

import slinky.core._
import slinky.core.annotations.react
import slinky.web.html._

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import org.scalajs.dom
import org.scalajs.dom.Event
import org.scalajs.dom.ext.Ajax

import scala.concurrent.{ExecutionContext, Future}
import scala.util.{Failure, Success}




@JSImport("resources/App.css", JSImport.Default)
@js.native
object AppCSS extends js.Object

@JSImport("import GridList from '@material-ui/core/GridList'", JSImport.Default)
@js.native
object GridList extends js.Object

@JSImport("resources/logo.svg", JSImport.Default)
@js.native
object ReactLogo extends js.Object

case class SprintItem(id: Long,
                      intervalAmount: Long,
                      intervalDuration: Long,
                      user: UserItem)

case class UserItem(id: Long, userName: String)

@react class App extends Component {
  type Props = Unit
  case class State(items: Seq[SprintItem], text: String)
  case class HttpBinResponse(origin: String, headers: Map[String, String])

  import scala.concurrent.ExecutionContext.Implicits.global

  def initApplication(e: Event) = getData.onComplete {
      case Success(v) => v.foreach(s => println(s))
      case Failure(e) => println(s"oops: $e")
    }

  def getData: Future[String] =
    Ajax.get("http://localhost:3000/api/series").map(_.responseText)

  override def initialState = {

    val test: Long = 777
    val name = "Vadim"

//    var items = "XXX"
//
//    getData.onComplete {
//      case Success(v) => v.foldLeft(items)((acc, el) => acc + el.toString)
//      case Failure(e) => println(s"oops: $e")
//    }

    State(
      Seq(
        SprintItem(0, test, test, UserItem(1, name)),
        SprintItem(1, 6, 30, UserItem(1, name))
      ),
      ""
    )
  }

  private val css = TodoAppCSS

  def render() = {
    div(className := "App")(
      header(className := "App-header")(
        img(
          src := TodoReactLogo.asInstanceOf[String],
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
      li(key := item.id.toString)(
        item.intervalAmount.toString + " " + item.user.userName
      )
    })
  }
}