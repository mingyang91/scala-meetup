package meetup.compare.legacy

import java.net.URI

import org.apache.pekko.pattern.after
import meetup.di.legacy.utils.Demo

import scala.concurrent.duration.DurationInt
import scala.concurrent.{Await, Future}

object Compare extends App with Demo {

  Future.successful(42)
  Future.failed(new Exception("¿"))

  Future {
    BigInt(-80538738812075974L).pow(3) +
      BigInt(80435758145817515L).pow(3) +
      BigInt(12602123297335631L).pow(3)
  }

  Future.unit

  object Connection {
    def query(sql: String): Future[Seq[String]] = Future successful Seq(sql)
  }
  def getConnection: Future[Connection.type] = Future successful Connection

  Connection.query("select name from user").map(rows => rows.distinct)

  getConnection.flatMap(_.query("SELECT * FROM table"))

  Connection.query("select * from user") zip Connection.query("select * from member")

  Connection.query("select * from table").recoverWith { _ =>
    Future.successful(Nil)
  }

  def chain[T, R](list: List[T])(f: T => Future[R]): Future[List[R]] =
    list match {
      case Nil => Future.successful(Nil)
      case head :: tail =>
        f(head).flatMap(hr => chain(tail)(f).map(tr => hr :: tr))
    }

  chain((1 to 100000).toList) { x =>
    Future(x * x)
  }

  Future.traverse((1 to 10000).toList) { x =>
    Future(x * x)
  }

  def loadRemoteData: Future[String] = {
    chain((1 to 10).toList) { i =>
      println(s"loading: ${i}0%")
      delay()
    }.map(_ => "data from remote")
  }

  def loadCacheData = {
    Future.successful("data from cache")
  }

  val race = Future.firstCompletedOf(List(loadRemoteData, loadCacheData))

  val res = Await.result(race, 30.seconds)
  println("返回： " + res)

  type User  = String
  type Image = Array[Byte]
  val image = Array.empty[Byte]

  def findUser(name: String): Future[Option[User]] = ???

  def retrieveAvatar(user: User): Future[Option[Image]] = ???

  for {
    user  <- findUser("mingyang91")
    image <- retrieveAvatar(user.get)
  } yield image

  def login(token: String): Future[Either[String, User]] = ???

  def upload(user: User, image: Image): Future[Either[String, URI]] = ???

  for {
    user <- login("a1b2c3")
    url  <- upload(user.getOrElse(""), image)
  } yield url

  def queryOrder() = delay(5.seconds).map(_ => "order info")

  Future.firstCompletedOf(List(delay().map(_ => "server is busy"), queryOrder()))

}

object Hello {
  import scala.concurrent.ExecutionContext.Implicits.global
  def main(args: Array[String]): Unit = {
    Future {
      println("hello")
    }
    scala.io.StdIn.readLine()
  }

}
