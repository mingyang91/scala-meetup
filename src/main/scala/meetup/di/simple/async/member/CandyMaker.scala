package meetup.di.simple.async.member

import zio.ZIO

class CandyMaker {

  def maskChocolate(name: String) = ZIO.unit

  def maskFruit(name: String) = ZIO.unit
}

object CandyMaker {
  def build: CandyMaker = new CandyMaker
}
