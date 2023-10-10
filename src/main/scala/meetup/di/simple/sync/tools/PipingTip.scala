package meetup.di.simple.sync.tools

import meetup.di.simple.sync.utils.Demo
import org.slf4j.LoggerFactory

import scala.concurrent.Future

class PipingTip extends Demo {
  val logger = LoggerFactory.getLogger(this.getClass)

  def load(content: String): Future[Unit] = {
    delay().map(_ => logger.info(s"$content 装入弹匣"))
  }

  def shaping(name: String): Future[Unit] = {
    delay().map(_ => logger.info(s"做造型 $name"))
  }
}

object PipingTip {
  def build: PipingTip = new PipingTip
}
