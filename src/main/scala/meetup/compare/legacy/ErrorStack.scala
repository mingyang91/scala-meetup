package meetup.compare.legacy

import meetup.di.legacy.utils.Demo

import scala.concurrent.duration.DurationInt
import scala.concurrent.{Await, ExecutionContextExecutor, Future}

object ErrorStack extends App with Demo {

  override implicit val ec: ExecutionContextExecutor = scala.concurrent.ExecutionContext.global

  def plus8(v: Int): Future[Int] = Future { v + 8 }

  def mul5(v: Int): Future[Int] = Future { v * 2 }

  def pow3(v: Int): Future[Int] = Future { Math.pow(v, 3).toInt }

  def div0(v: Int): Future[Int] = Future { v / 0 }

  val f = for {
    a1  <- plus8(1)
    a2  <- mul5(a1)
    a3  <- pow3(a2)
    res <- div0(a3)
  } yield res

  println(Await.result(f, 1.seconds))

}
