package meetup.di.simple.async.utils

import zio.clock.Clock
import zio.{URIO, ZIO}
import zio.duration.{durationInt, Duration}

trait Demo {
  def delay(duration: Duration = 1.seconds): URIO[Clock, Unit] = ZIO.sleep(duration)
}

object Demo {
  def build: Demo = new Demo {
    //
  }
}
