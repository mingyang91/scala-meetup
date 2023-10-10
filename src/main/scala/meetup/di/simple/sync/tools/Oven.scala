package meetup.di.simple.sync.tools

import meetup.di.simple.sync.utils.Demo
import org.slf4j.LoggerFactory

import scala.concurrent.Future

class Oven extends Demo {

  val logger = LoggerFactory.getLogger(this.getClass)

  def baking(): Future[Unit] = {
    for {
      _ <- delay()
      _ = logger.info("开始烘烤")
      _ <- delay()
      _ = logger.info("10 分钟过去了")
      _ <- delay()
      _ = logger.info("20 分钟过去了")
      _ <- delay()
      _ = logger.info("30 分钟过去了")
      _ <- delay()
      _ = logger.info("40 分钟过去了")
      _ <- delay()
    } yield logger.info("烘烤完成")
  }
}

object Oven {
  def build: Oven = new Oven
}
