package meetup.di.simple.sync.member

import org.slf4j.LoggerFactory

class Accountant {

  val logger = LoggerFactory.getLogger(this.getClass)

  def bookkeeping() = logger.info("财务在记账")
}

object Accountant {
  def build: Accountant = new Accountant
}
