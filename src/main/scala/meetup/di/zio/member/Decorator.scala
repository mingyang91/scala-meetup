package meetup.di.zio.member

import meetup.di.zio.tools.{Mixer, PipingTip, Turntable}
import meetup.di.zio.utils.Demo
import zio.{Has, ZIO}
import zio.clock.Clock
import zio.logging.Logging

object Decorator extends Demo {

  def decorating(cake: String): ZIO[Logging with Clock with Has[PipingTip] with Has[Turntable] with Has[Mixer], Nothing, String] = {
    for {
      mixer     <- ZIO.service[Mixer]
      turntable <- ZIO.service[Turntable]
      pipingTip <- ZIO.service[PipingTip]
      mixed     <- mixer.mixing("黄油", "蛋白", "牛奶", "白砂糖")
      _         <- turntable.spring(cake)
      _         <- pipingTip.load(mixed)
      _         <- pipingTip.shaping("花❀花")
    } yield "漂亮花❀花"
  }
}
