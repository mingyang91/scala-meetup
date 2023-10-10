package meetup.di.simple.async.member

import meetup.di.simple.async.tools._
import meetup.di.simple.async.utils.Demo
import zio.clock.Clock
import zio.logging.Logging
import zio.{Has, ZIO}

class Baker(oven: Oven, mixer: Mixer) extends Demo {

  def makeCakeBase(): ZIO[Logging with Clock, Nothing, String] = {
    for {
      _ <- Logging.info("开始制作蛋糕饼")
      _ <- mixer.mixing("蛋清", "白糖", "面粉", "纯净水")
      _ = Logging.info("倒入模具")
      _ <- mixer.mixing("蛋黄", "白糖", "面粉", "纯净水")
      _ = Logging.info("倒入模具")
      _ <- oven.baking()
    } yield "蛋糕"
  }

}

object Baker {
  def build(implicit oven: Oven, mixer: Mixer): Baker = new Baker(implicitly, implicitly)
}
