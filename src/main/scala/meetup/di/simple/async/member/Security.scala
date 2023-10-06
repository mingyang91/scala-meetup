package meetup.di.simple.async.member

import zio.logging.Logging

class Security {

  def patrol() = Logging.info("保安在巡逻")

}

object Security {
  def build: Security = new Security
}
