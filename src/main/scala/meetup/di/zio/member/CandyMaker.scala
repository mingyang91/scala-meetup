package meetup.di.zio.member

import zio.ZIO

object CandyMaker {

  def maskChocolate(name: String) = ZIO.unit

  def maskFruit(name: String) = ZIO.unit
}
