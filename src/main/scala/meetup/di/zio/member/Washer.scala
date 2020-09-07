package meetup.di.zio.member

import zio.logging.Logging

object Washer {
  def washing() = Logging.info("刷碗工一直在刷碗")
}
