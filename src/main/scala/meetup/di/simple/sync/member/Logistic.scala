package meetup.di.simple.sync.member

import org.slf4j.LoggerFactory

class Logistic {

  val logger = LoggerFactory.getLogger(this.getClass)

  def supplement() = logger.info("后勤每天补充食材")

}

object Logistic {
  def build: Logistic = new Logistic
}
