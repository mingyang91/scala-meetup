package meetup.di.simple.async

import meetup.di.simple.async.member.{
  Accountant,
  Baker,
  CandyMaker,
  Cashier,
  Decorator,
  HunanChef,
  Logistic,
  Manager,
  Security,
  SichuanChef,
  Waiter,
  Washer
}
import meetup.di.simple.async.tools.{Mixer, Oven, PipingTip, Turntable}
import zio._
import zio.logging.Logging
import zio.logging.slf4j.Slf4jLogger

object djx314 extends App {

  override def run(args: List[String]): URIO[zio.ZEnv, ExitCode] = {

    val io = for {
      fiber <- AppInjection.cakeShop.opening.fork
      cake  <- AppInjection.cakeShop.甜在心蛋糕()
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

  object AppInjection {
    implicit val mixer: Mixer           = Mixer.build
    implicit val oven: Oven             = Oven.build
    implicit val pipingTip: PipingTip   = PipingTip.build
    implicit val turntable: Turntable   = Turntable.build
    implicit val baker: Baker           = Baker.build
    implicit val decorator: Decorator   = Decorator.build
    implicit val candyMaker: CandyMaker = CandyMaker.build
    implicit val scChef: SichuanChef    = SichuanChef.build
    implicit val hnChef: HunanChef      = HunanChef.build
    implicit val cashier: Cashier       = Cashier.build
    implicit val waiter: Waiter         = Waiter.build
    implicit val washer: Washer         = Washer.build
    implicit val logistic: Logistic     = Logistic.build
    implicit val security: Security     = Security.build
    implicit val accountant: Accountant = Accountant.build
    implicit val manager: Manager       = Manager.build
    val cakeShop: CakeShop              = CakeShop.build
  }

}
