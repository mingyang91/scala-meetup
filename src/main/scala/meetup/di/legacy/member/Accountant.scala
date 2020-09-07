package meetup.di.legacy.member

import org.slf4j.LoggerFactory

class Accountant {

  val logger = LoggerFactory.getLogger(this.getClass)

  def bookkeeping() = logger.info("财务在记账")
}
