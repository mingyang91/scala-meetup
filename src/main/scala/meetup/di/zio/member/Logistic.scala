package meetup.di.zio.member

import zio.logging.Logging

object Logistic {

  def supplement() = Logging.info("后勤每天补充食材")

}
