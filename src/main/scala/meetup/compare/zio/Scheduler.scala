package meetup.compare.zio
import java.time.DateTimeException

import zio.clock.Clock
import zio.duration.durationInt
import zio.logging.Logging
import zio.{ExitCode, Schedule, URIO, ZIO}

object Scheduler extends zio.App {
  override def run(args: List[String]): URIO[zio.ZEnv, ExitCode] = {

    val io = for {
      _ <- start
      _ <- process.repeat(Schedule.spaced(10.seconds)).delay(3.seconds)
    } yield ()

    io.provideSomeLayer[zio.ZEnv](logger).exitCode
  }

  val start: ZIO[Logging with Clock, DateTimeException, Unit] = for {
    now <- zio.clock.currentDateTime
    _   <- Logging.info(s"start at ${now.toLocalTime}")
  } yield ()

  val process: ZIO[Logging with Clock, DateTimeException, Unit] = for {
    now <- zio.clock.currentDateTime
    _   <- Logging.debug(s"run at ${now.toLocalTime}")
  } yield ()

  val logger = Logging.console(rootLoggerName = Some(this.getClass.getName))
}
