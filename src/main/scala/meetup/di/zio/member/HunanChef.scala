package meetup.di.zio.member

import zio.ZIO
import zio.logging.Logging

object HunanChef {

  def cooking(name: String): ZIO[Logging, Nothing, String] = Logging.info("湘菜师傅在做 " + name) *> ZIO.succeed(name)
}
