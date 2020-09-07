package meetup.di.zio.member

import zio.logging.Logging

object Security {

  def patrol() = Logging.info("保安在巡逻")
}
