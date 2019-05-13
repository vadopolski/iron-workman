package com.ironworkman

import slinky.core.{Component, StatelessComponent, SyntheticEvent}
import slinky.web.html.{div, _}
import org.scalajs.dom.{Event, html}

import scala.scalajs.js.Date
import slinky.core.annotations.react
import slinky.core.Component
import slinky.web.html._
import org.scalajs.dom.window._

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

case class IntervalItem(id: Long, paidTime: Long, dontStopPayingTime: Long, dontPayTime: Long, description: String)

@JSImport("resources/App.css", JSImport.Default)
@js.native
object TodoAppCSS extends js.Object

@JSImport("resources/logo.svg", JSImport.Default)
@js.native
object TodoReactLogo extends js.Object


@react class TodoApp extends Component {
  type Props = Unit
  case class State(items: Seq[IntervalItem], description: String, paidTime: Long, seconds: Long)

  private var interval = -1

  override def componentDidMount(): Unit = {
    interval = setInterval(() => tick(), 1000)
  }

  override def componentWillUnmount(): Unit = {
    clearInterval(interval)
  }

  def tick(): Unit = {
    if (state.seconds == 0) {
      clearInterval(interval)
      interval = setInterval(() => tick(), 1000)
    } else {
      setState(prevState => prevState.copy(seconds = prevState.seconds - 1))
    }
  }

  override def initialState = State(Seq.empty, "", 0, 30)

  def handleChange(e: SyntheticEvent[html.Input, Event]): Unit = {
    val eventValue = e.target.value
    setState(_.copy(description = eventValue))
  }

  def handleChange2(e: SyntheticEvent[html.Input, Event]): Unit = {
    val eventValue = e.target.value
    setState(_.copy(paidTime = eventValue.toLong))
  }

  def handleSubmit(e: SyntheticEvent[html.Form, Event]): Unit = {
    e.preventDefault()

    clearInterval(interval)
    interval = setInterval(() => tick(), 1000)

    if (state.description.nonEmpty) {
      val newItem = IntervalItem(
        id = Date.now().toLong,
        description = state.description,
        paidTime = state.paidTime,
        dontStopPayingTime = 0,
        dontPayTime =0
      )

      setState(prevState => {
        State(
          items = prevState.items :+ newItem,
          description = "",
          paidTime = 0,
          seconds = 30
        )
      })
    }
  }

  private val css = TodoAppCSS

  override def render() = {
    div(className := "App")(
      header(className := "App-header")(
        img(
          src := TodoReactLogo.asInstanceOf[String],
          className := "App-logo",
          alt := "logo"
        ),
        h1(className := "App-title")("Welcome to Iron Workman App")
      ),
      h3("Iron Work Man"),
      h3("Seconds: ", state.seconds.toString),
      form(onSubmit := (handleSubmit(_)))(
        input(
          onChange := (handleChange(_)),
          value := state.description
        ),
        input(
          onChange := (handleChange2(_)),
          value := state.paidTime.toString
        ),
        button(s"Add #${state.items.size + 1}")
      ),
      TodoList(items = state.items)
    )
  }
}

@react class TodoList extends StatelessComponent {
  case class Props(items: Seq[IntervalItem])

  override def render() = {
    div(className := "col-md-6")(
      div(className := "card")(
        ul(className := "list-group")(
          props.items.map { item =>
            li(key := item.id.toString)(s"Paid time: ${item.paidTime}, Description: ${item.description}")
          }
        )
      ),
      div(s"Total Paid time = ${props.items.foldLeft(0L)((acc, item) => acc + item.paidTime)}")
    )
  }
}

@react class TodoItem extends StatelessComponent {
  case class Props(items: Seq[IntervalItem])

  override def render() = {
    div(className := "col-md-6")(
      div(className := "card", style := js.Dynamic.literal(
        width = "100%"
      ))(
        ul(className := "list-group")(
          props.items.map { item =>
            li(key := item.id.toString)(s"Paid time: ${item.paidTime}, Description: ${item.description}")
          }
        )
      ),
      div(s"Total Paid time = ${props.items.foldLeft(0L)((acc, item) => acc + item.paidTime)}")
    )
  }
}