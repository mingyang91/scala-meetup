package meetup.di.zio.utils

import zio.clock.Clock
import zio.{URIO, ZIO}
import zio.duration.{Duration, durationInt}


trait Demo {
  def delay(duration: Duration = 1.seconds): URIO[Clock, Unit] = ZIO.sleep(duration)
}
