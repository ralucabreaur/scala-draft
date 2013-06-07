package com.endava

import org.scalatra.test.scalatest._

import org.junit.runner.RunWith
import org.scalatest.matchers._
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner]) // makes test run with Maven Surefire
class ArticlesControllerSuite extends ScalatraFunSuite with ShouldMatchers {
  addServlet(classOf[ArticlesController], "/*")

  test("GET XML /articles/5 returns status 200 with xml response") {
    get("/articles/5", Seq.empty, Map("Accept" -> "application/xml")) {
      status should equal(200)
      response.getContentType().contains("application/xml") should be(true)
    }
  }

  test("GET JSON /articles/5 returns status 200 with json response") {
    get("/articles/5", Seq.empty, Map("Accept" -> "application/json")) {
      status should equal(200)
      response.getContentType().contains("application/json") should be(true)
    }
  }
}
