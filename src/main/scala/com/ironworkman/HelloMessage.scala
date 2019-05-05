package com.ironworkman

import com.softwaremill.sttp.{Id, Response}
import slinky.core._
import slinky.core.annotations.react
import slinky.core.facade.ReactElement
import slinky.web.html._
import com.softwaremill.sttp.quick._


@react class HelloMessage extends StatelessComponent {
  case class Props(name: String)

  override def render(): ReactElement = {
    div("Hello ", props.name)
  }
}