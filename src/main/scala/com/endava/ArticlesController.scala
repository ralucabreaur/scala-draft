package com.endava

import org.scalatra.{Ok, ScalatraServlet}
import org.json4s.{Formats, DefaultFormats}
import org.scalatra.json.JacksonJsonSupport

class ArticlesController extends ScalatraServlet with JacksonJsonSupport {

  protected implicit val jsonFormats: Formats = DefaultFormats


  get("/articles/:id") {
    val result = Map(
      "list" -> List(7, "55", 39),
      "number" -> 55,
      "string" -> "something"
    )
    Ok(result)
  }

  post("/articles") {
    Ok("hello")
  }

  put("/articles/:id") {
    Ok("hello")
  }

  delete("/articles/:id") {
    Ok("hello")
  }
}
