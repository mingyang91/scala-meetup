package meetup.di.legacy.member

import org.slf4j.LoggerFactory

class Manager {

  val logger = LoggerFactory.getLogger(this.getClass)

  def scheduling() = logger.info("店长在协调人手")
}
