import com.endava.ArticlesController
import org.scalatra.LifeCycle
import javax.servlet.ServletContext

class ScalatraBootstrap extends LifeCycle {
  override def init(context: ServletContext) {
    context mount(new ArticlesController, "/*")
  }
}