package meetup.di.simple.async.member

import zio.logging.Logging

class Waiter {

  def onCall() = Logging.info("服务员等待客户呼唤")

}

object Waiter {
  def build: Waiter = new Waiter
}
