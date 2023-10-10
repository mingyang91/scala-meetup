package meetup.di.simple.async.member

import zio.ZIO
import zio.logging.Logging

class HunanChef {

  def cooking(name: String): ZIO[Logging, Nothing, String] = Logging.info("湘菜师傅在做 " + name) *> ZIO.succeed(name)

}

object HunanChef {
  def build: HunanChef = new HunanChef
}
