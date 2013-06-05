package com.endava

import org.scalatra.{Ok, ScalatraServlet}

class Articles extends ScalatraServlet {

  get("/articles/:id") {
    Ok("hello")
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
