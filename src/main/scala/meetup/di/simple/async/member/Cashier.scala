package meetup.di.simple.async.member

import meetup.di.simple.async.utils.Demo
import zio.logging.Logging

class Cashier extends Demo {

  def standby() = Logging.info("收银员等待顾客结账")

}

object Cashier {
  def build: Cashier = new Cashier
}
