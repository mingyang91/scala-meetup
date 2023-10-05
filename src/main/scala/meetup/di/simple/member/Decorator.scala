package meetup.di.simple.member

import meetup.di.simple.tools.{Mixer, PipingTip, Turntable}
import meetup.di.simple.utils.Demo

class Decorator(mixer: Mixer, pipingTip: PipingTip, turntable: Turntable) extends Demo {

  def decorating(cake: String) = {
    for {
      mixed <- mixer.mixing("黄油", "蛋白", "牛奶", "白砂糖")
      _     <- turntable.spring(cake)
      _     <- pipingTip.load(mixed)
      _     <- pipingTip.shaping("花❀花")
    } yield "漂亮花❀花"
  }
}
