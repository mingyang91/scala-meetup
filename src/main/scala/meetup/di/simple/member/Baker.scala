package meetup.di.simple.member

import meetup.di.simple.tools._
import meetup.di.simple.utils.Demo
import org.slf4j.LoggerFactory

import scala.concurrent.Future

class Baker(oven: Oven, mixer: Mixer) extends Demo {
  val logger = LoggerFactory.getLogger(this.getClass)

  def makeCakeBase(): Future[String] = {
    logger.info("开始制作蛋糕饼")
    for {
      _ <- mixer.mixing("蛋清", "白糖", "面粉", "纯净水")
      _ = logger.info("倒入模具")
      _ <- mixer.mixing("蛋黄", "白糖", "面粉", "纯净水")
      _ = logger.info("倒入模具")
      _ <- oven.baking()
    } yield "蛋糕"
  }

}
