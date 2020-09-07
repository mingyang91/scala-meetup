package meetup.compare.zio

import java.net.URI

import zio._
import zio.clock.Clock
import zio.duration.durationInt
import zio.logging.Logging
import zio.logging.slf4j.Slf4jLogger
import zio.random.Random
import zio.stm.{STM, TArray, ZSTM}

object Compare extends App {
  val logger = Logging.console(rootLoggerName = Some(this.getClass.getName))

  override def run(args: List[String]): URIO[zio.ZEnv, ExitCode] = race.provideSomeLayer[ZEnv](logger).exitCode

  ZIO.succeed(42)
  ZIO.fail(new Exception("¿"))

  ZIO {
    BigInt(-80538738812075974L).pow(3) +
      BigInt(80435758145817515L).pow(3) +
      BigInt(12602123297335631L).pow(3)
  }

  ZIO.unit

  object Connection {
    def query(sql: String): Task[Seq[String]] = Task succeed Seq(sql)
  }
  def getConnection: Task[Connection.type] = Task succeed Connection

  Connection.query("select name from user")
    .map(rows => rows.distinct)

  getConnection
    .flatMap(_.query("SELECT * FROM table"))

  Connection.query("select * from user") zip Connection.query("select * from member")

  Connection.query("select * from table")
    .catchAll { _ =>
      ZIO.succeed(Nil)
    }

  ZIO.foreach((1 to 10000).toList) { x =>
    ZIO(x * x)
  }

  ZIO.foreachParN(8)((1 to 10000).toList) { x =>
    ZIO(x * x)
  }

  def loadRemoteData: ZIO[Clock with Logging, Nothing, String] = {
    Logging.info("open remote connection")
      .bracket(_ => Logging.info("close remote connection")) { _ =>
        ZIO.foreach((1 to 10).toList) { i =>
          Logging.info(s"loading: ${i}0%") *> ZIO.sleep(1.seconds)
        }
          .as("data from remote")
          .ensuring(Logging.info("finally"))
      }
  }

  def loadCacheData: ZIO[Clock, Nothing, String] = {
    ZIO.sleep(1.seconds) *> ZIO.succeed("data from cache")
  }

  val race = loadRemoteData race loadCacheData
    .tap(data => Logging.info("返回: " + data))

  type User = String
  type Image = Array[Byte]
  val image = Array.empty[Byte]

  def findUser(name: String)
        : IO[Option[Nothing], User] = ???
  def retrieveAvatar(user: User)
        : IO[Option[Nothing], Image] = ???

  for {
    user <- findUser("mingyang91")
    image <- retrieveAvatar(user)
  } yield image

  def login(token: String): IO[String, User] = ???

  def upload(user: User,
             image: Image): IO[String, URI] = ???

  for {
    user <- login("a1b2c3")
    url <- upload(user, image)
  } yield url

  def queryOrder(): ZIO[Clock, Nothing, String] = ZIO.sleep(5.seconds).as("order info")

  queryOrder().timeoutFail("server is busy")(1.seconds)

}

object Hello {
  def main(args: Array[String]): Unit = {
    ZIO {
      println("hello")
    }
    scala.io.StdIn.readLine()
  }
}