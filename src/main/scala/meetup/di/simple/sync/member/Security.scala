package meetup.di.simple.sync.member

import org.slf4j.LoggerFactory

class Security {

  val logger = LoggerFactory.getLogger(this.getClass)

  def patrol() = logger.info("保安在巡逻")
}

object Security {
  def build: Security = new Security
}
