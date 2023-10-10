package meetup.di.simple.sync.member

import scala.concurrent.Future

class SichuanChef {

  def cooking(name: String): Future[Unit] = ???
}

object SichuanChef {
  def build: SichuanChef = new SichuanChef
}
