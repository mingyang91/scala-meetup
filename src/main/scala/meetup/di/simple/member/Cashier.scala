package meetup.di.simple.member

import meetup.di.simple.utils.Demo
import org.slf4j.LoggerFactory

class Cashier extends Demo {

  val logger = LoggerFactory.getLogger(this.getClass)

  def standby() = logger.info("收银员等待顾客结账")

}
