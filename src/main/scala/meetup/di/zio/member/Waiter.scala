package meetup.di.zio.member

import zio.logging.Logging


object Waiter {

  def onCall() = Logging.info("服务员等待客户呼唤")
}
