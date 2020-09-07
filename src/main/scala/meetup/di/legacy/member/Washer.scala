package meetup.di.legacy.member

import org.slf4j.LoggerFactory

class Washer {

  val logger = LoggerFactory.getLogger(this.getClass)

  def washing() = logger.info("刷碗工一直在刷碗")
}
