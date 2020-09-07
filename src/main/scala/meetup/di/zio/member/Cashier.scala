package meetup.di.zio.member

import meetup.di.zio.utils.Demo
import zio.logging.Logging


object Cashier extends Demo {

  def standby() = Logging.info("收银员等待顾客结账")

}
