package meetup.di.simple.sync.member

import scala.concurrent.Future

class CandyMaker {

  def maskChocolate(name: String) = Future.unit

  def maskFruit(name: String) = Future.unit
}

object CandyMaker {
  def build: CandyMaker = new CandyMaker
}
