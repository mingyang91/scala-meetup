package meetup.di.zio.tools

import meetup.di.zio.utils.Demo
import zio.ZIO
import zio.clock.Clock
import zio.logging.Logging

class Turntable extends Demo {

  def spring(cake: String): ZIO[Logging with Clock, Nothing, Unit] = {
    delay() *> Logging.info(s"$cake 转起来")
  }
}
