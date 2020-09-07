package meetup.di.zio.member

import zio.{UIO, ZIO}

import scala.concurrent.Future

object SichuanChef {

  def cooking(name: String): UIO[Unit] = ZIO.unit
}
