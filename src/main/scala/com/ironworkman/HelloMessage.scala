package com.ironworkman

import slinky.core._
import slinky.core.annotations.react
import slinky.core.facade.ReactElement
import slinky.web.html._

import scala.scalajs.js
import scala.scalajs.js.annotation.{JSImport, ScalaJSDefined}


@react class HelloMessage extends StatelessComponent {
  case class Props(name: String)

  override def render(): ReactElement = {
    div("Hello ", props.name)
  }
}