package bdd

import cucumber.api.scala._
import org.scalatest.matchers._
import scala.collection.mutable.{Map=> MutableMap}
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner


@RunWith(classOf[JUnitRunner])
class MultiplyNumbersTest extends ScalaDsl with EN with ShouldMatchers{

  val vars = MutableMap[String,Int]()
  var result = 0

  Given("""^a variable ([a-z]+) with value (\d+)$"""){ (varName:String, value:Int) =>
    vars += varName -> value
  }
  When("""^I multiply ([a-z]+) \* ([a-z]+)$"""){ (var1:String, var2:String) =>
    result = vars(var1) * vars(var2)
  }
  Then("""^I get (\d+)$"""){ (expectedResult:Int) =>
    assert(result === expectedResult)
  }


}
