package meetup.di.zio.member

import zio.logging.Logging

object Manager {

  def scheduling() = Logging.info("店长在协调人手")
}
