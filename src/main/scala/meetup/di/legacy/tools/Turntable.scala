package meetup.di.legacy.tools

import meetup.di.legacy.utils.Demo
import org.slf4j.LoggerFactory

import scala.concurrent.Future

class Turntable extends Demo {

  val logger = LoggerFactory.getLogger(this.getClass)

  def spring(cake: String): Future[Unit] = {
    delay().map(_ => logger.info(s"$cake 转起来"))
  }
}
