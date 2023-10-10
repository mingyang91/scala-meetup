package meetup.di.simple.async.member

import zio.{UIO, ZIO}

import scala.concurrent.Future

class SichuanChef {

  def cooking(name: String): UIO[Unit] = ZIO.unit

}

object SichuanChef {
  def build: SichuanChef = new SichuanChef
}
