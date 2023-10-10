package meetup.di.simple.sync.member

import org.slf4j.LoggerFactory

class Waiter {

  val logger = LoggerFactory.getLogger(this.getClass)

  def onCall() = logger.info("服务员等待客户呼唤")
}

object Waiter {
  def build: Waiter = new Waiter
}
