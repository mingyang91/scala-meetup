package meetup.di.simple.async.member

import zio.logging.Logging

class Logistic {

  def supplement() = Logging.info("后勤每天补充食材")

}

object Logistic {
  def build: Logistic = new Logistic
}
