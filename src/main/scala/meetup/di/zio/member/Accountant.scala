package meetup.di.zio.member

import zio.ZIO
import zio.logging.Logging

object Accountant {

  def bookkeeping() = ZIO.effectTotal(Logging.info("财务在记账"))
}
