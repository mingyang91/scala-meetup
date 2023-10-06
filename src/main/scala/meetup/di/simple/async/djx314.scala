package meetup.di.simple.async

import meetup.di.simple.async.tools.{Mixer, Oven, PipingTip, Turntable}
import zio._
import zio.logging.Logging
import zio.logging.slf4j.Slf4jLogger

object djx314 extends App {

  override def run(args: List[String]): URIO[zio.ZEnv, ExitCode] = {

    val io = for {
      fiber <- CakeShop.opening.fork
      cake  <- CakeShop.甜在心蛋糕()
      _     <- Logging.info(cake)
      _     <- fiber.await
    } yield cake

    io.provideSomeLayer[zio.ZEnv](logger ++ tools).exitCode
  }

  val logger         = Logging.console(rootLoggerName = Some(this.getClass.getName))
  val pipingTipLayer = ZLayer.succeed(new PipingTip)
  val turntableLayer = ZLayer.succeed(new Turntable)
  val mixerLayer     = ZLayer.succeed(new Mixer)
  val ovenLayer      = ZLayer.succeed(new Oven)
  val tools          = pipingTipLayer ++ turntableLayer ++ mixerLayer ++ ovenLayer

}
