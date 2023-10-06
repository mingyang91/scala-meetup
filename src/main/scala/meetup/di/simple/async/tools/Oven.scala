package meetup.di.simple.async.tools

import meetup.di.simple.async.utils.Demo
import zio.URIO
import zio.clock.Clock
import zio.logging.Logging

class Oven extends Demo {

  def baking(): URIO[Logging with Clock, Unit] = {
    for {
      _ <- delay()
      _ <- Logging.info("开始烘烤")
      _ <- delay()
      _ <- Logging.info("10 分钟过去了")
      _ <- delay()
      _ <- Logging.info("20 分钟过去了")
      _ <- delay()
      _ <- Logging.info("30 分钟过去了")
      _ <- delay()
      _ <- Logging.info("40 分钟过去了")
      _ <- delay()
      _ <- Logging.info("烘烤完成")
    } yield ()
  }
}

object Oven {
  def build: Oven = new Oven
}
