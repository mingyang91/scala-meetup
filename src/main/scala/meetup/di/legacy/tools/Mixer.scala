package meetup.di.legacy.tools

import meetup.di.legacy.utils.Demo
import org.slf4j.LoggerFactory

import scala.concurrent.Future

class Mixer extends Demo {

  val logger = LoggerFactory.getLogger(this.getClass)

  def mixing(name: String, names: String*): Future[String] = {
    val mixed = name +: names
    val zero = Future(logger.info(s"$name 开始搅拌")).flatMap(_ => delay())

    names.foldLeft(zero) { (acc, n) =>
      acc.flatMap { _ =>
        logger.info(s"加入 $n 继续搅拌")
        delay()
      }
    }
      .map(_ => logger.info(s"${mixed.mkString(",")} 已搅拌完成"))
      .flatMap(_ => delay())
      .map(_ => mixed.mkString(",") + " 的混合物")
  }

}
