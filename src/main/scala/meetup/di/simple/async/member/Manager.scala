package meetup.di.simple.async.member

import zio.logging.Logging

class Manager {
  def scheduling() = Logging.info("店长在协调人手")
}

object Manager {
  def build: Manager = new Manager
}
