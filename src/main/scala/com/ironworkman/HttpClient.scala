package com.ironworkman

import com.softwaremill.sttp._

object HttpClient {

  val sort: Option[String] = None
  val query = "http language:scala"
  val request = sttp.get(uri"https://api.github.com/search/repositories?q=$query&sort=$sort")
  implicit val backend = HttpURLConnectionBackend()
  val response = request.send()

  def main(args: Array[String]): Unit = {
    // response.unsafeBody: by default read into a String
    println(response.unsafeBody.take(15))

  }
}
