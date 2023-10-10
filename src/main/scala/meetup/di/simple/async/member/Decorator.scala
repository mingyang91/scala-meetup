package meetup.di.simple.async.member

import meetup.di.simple.async.tools.{Mixer, PipingTip, Turntable}
import meetup.di.simple.async.utils.Demo
import zio.{Has, ZIO}
import zio.clock.Clock
import zio.logging.Logging

class Decorator(pipingTip: PipingTip, turntable: Turntable, mixer: Mixer) extends Demo {

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

object Decorator {
  def build(implicit pipingTip: PipingTip, turntable: Turntable, mixer: Mixer): Decorator =
    new Decorator(implicitly, implicitly, implicitly)
}
