package meetup.di.simple.sync.member

import meetup.di.simple.sync.utils.Demo
import org.slf4j.LoggerFactory

class Cashier extends Demo {

  val logger = LoggerFactory.getLogger(this.getClass)

  def standby() = logger.info("收银员等待顾客结账")

}

object Cashier {
  def build: Cashier = new Cashier
}
