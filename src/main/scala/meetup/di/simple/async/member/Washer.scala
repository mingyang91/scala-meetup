package meetup.di.simple.async.member

import zio.logging.Logging

class Washer {

  def washing() = Logging.info("刷碗工一直在刷碗")

}

object Washer {
  def build: Washer = new Washer
}
