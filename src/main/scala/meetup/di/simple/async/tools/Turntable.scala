package meetup.di.simple.async.tools

import meetup.di.simple.async.utils.Demo
import zio.ZIO
import zio.clock.Clock
import zio.logging.Logging

class Turntable extends Demo {

  def spring(cake: String): ZIO[Logging with Clock, Nothing, Unit] = {
    delay() *> Logging.info(s"$cake 转起来")
  }

}

object Turntable {
  def build: Turntable = new Turntable
}
