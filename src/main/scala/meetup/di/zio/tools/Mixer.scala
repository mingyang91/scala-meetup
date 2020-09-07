package meetup.di.zio.tools

import meetup.di.zio.utils.Demo
import zio.clock.Clock
import zio.{Task, URIO, ZIO}
import zio.logging.Logging

class Mixer extends Demo {

  def mixing(name: String, names: String*): URIO[Clock with Logging, String] = {
    val mixed = name +: names
    for {
      _ <- Logging.info(s"$name 开始搅拌")
      _ <- delay()
      _ <- ZIO.foreach(names) { n =>
        Logging.info(s"加入 $n 继续搅拌") *> delay()
      }
      _ <- Logging.info(s"${mixed.mkString(",")} 已搅拌完成")
    } yield mixed.mkString(",") + " 的混合物"
  }

}
