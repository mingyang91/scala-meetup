package meetup.compare.zio
import zio.clock.Clock
import zio.duration.durationInt
import zio.logging.Logging
import zio.random.Random
import zio.{ExitCode, URIO, ZIO}

object request extends zio.App {
  override def run(args: List[String]): URIO[zio.ZEnv, ExitCode] = {

    request()
      .timeoutFail("fail")(4.seconds)
      .retryN(10)
      .flatMap(msg => Logging.info(msg))
      .flatMapError(err => Logging.error(err))
      .provideSomeLayer[zio.ZEnv](logger)
      .exitCode
  }

  val logger = Logging.console(rootLoggerName = Some("request"))

  def request(): ZIO[Clock with Logging with Random, Nothing, String] = {

    val io = for {
      delay <- zio.random.nextIntBetween(1, 10000)
      _     <- Logging.info(s"request start, Estimated waiting time ${delay}ms")
      fiber <- Logging.debug("processing").delay(1.seconds).forever.fork
      _     <- ZIO.sleep(delay.milliseconds)
      _     <- fiber.interrupt
    } yield "success"

    io.onInterrupt(_ => Logging.warn("cancelled"))
  }
}
