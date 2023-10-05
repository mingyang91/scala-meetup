package meetup.di.simple.member

import scala.concurrent.Future

class HunanChef {

  def cooking(name: String): Future[Unit] = ???
}

object HunanChef {
  def build: HunanChef = new HunanChef
}
