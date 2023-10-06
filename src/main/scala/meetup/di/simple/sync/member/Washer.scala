package meetup.di.simple.sync.member

import org.slf4j.LoggerFactory

class Washer {

  val logger = LoggerFactory.getLogger(this.getClass)

  def washing() = logger.info("刷碗工一直在刷碗")
}

object Washer {
  def build: Washer = new Washer
}
