package meetup.di.simple.async.member

import zio.ZIO
import zio.logging.Logging

class Accountant {
  def bookkeeping() = ZIO.effectTotal(Logging.info("财务在记账"))
}

object Accountant {
  def build: Accountant = new Accountant
}
