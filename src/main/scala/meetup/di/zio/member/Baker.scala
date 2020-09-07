package meetup.di.zio.member

import meetup.di.zio.tools._
import meetup.di.zio.utils.Demo
import zio.clock.Clock
import zio.logging.Logging
import zio.{Has, ZIO}

object Baker extends Demo {

  def makeCakeBase(): ZIO[Logging with Clock with Has[Oven] with Has[Mixer], Nothing, String] = {
    for {
      mixer <- ZIO.service[Mixer]
      oven <- ZIO.service[Oven]
      _ <- Logging.info("开始制作蛋糕饼")
      _ <- mixer.mixing("蛋清", "白糖", "面粉", "纯净水")
      _ = Logging.info("倒入模具")
      _ <- mixer.mixing("蛋黄", "白糖", "面粉", "纯净水")
      _ = Logging.info("倒入模具")
      _ <- oven.baking()
    } yield "蛋糕"
  }

}
