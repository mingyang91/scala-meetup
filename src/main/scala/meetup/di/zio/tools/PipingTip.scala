package meetup.di.zio.tools

import meetup.di.zio.utils.Demo
import zio.ZIO
import zio.clock.Clock
import zio.logging.Logging

class PipingTip extends Demo {

  def load(content: String): ZIO[Logging with Clock, Nothing, Unit] = {
    delay() *> Logging.info(s"$content 装入弹匣")
  }

  def shaping(name: String): ZIO[Logging with Clock, Nothing, Unit] = {
    delay() *> Logging.info(s"做造型 $name")
  }
}
