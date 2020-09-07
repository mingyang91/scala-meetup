package meetup.compare.zio

import zio.clock.Clock
import zio.duration.durationInt
import zio.logging.Logging
import zio.random.Random
import zio.stm.{STM, TArray}
import zio._

object Quorum extends zio.App {

  override def run(args: List[String]): URIO[zio.ZEnv, ExitCode] = {
    readQuorum()
      .flatMap(v => Logging.info(s"quorum $v"))
      .provideSomeLayer[zio.ZEnv](Logging.console(rootLoggerName = Some("Quorum")))
      .exitCode
  }

  Math.random()

  val shards: Seq[Int] = (0 to 5).toList

  def shardInit: IO[Nothing, TArray[Option[String]]] = STM.atomically(TArray.make[Option[String]](None, None, None, None, None, None))

  def readShard(shardId: Int): ZIO[Clock with Random with Logging, String, String] = {
    val io = for {
      _ <- Logging.info(s"Shard: $shardId read start")
      rand <- zio.random.nextIntBetween(0, 10000)
      _ <- Logging.debug(s"Shard: $shardId backlog is $rand")
      _ <- ZIO.sleep(rand.milliseconds)
//      _ <- if (rand % 3 == 0) throw new Exception("oh my god") else ZIO.unit
      v <- if (rand % 10 == 2) ZIO.succeed("Error") else ZIO.succeed("Succeed")
      _ <- Logging.info(s"Shard: $shardId read $v")
    } yield v

    io.onInterrupt(Logging.warn(s"Shard: $shardId read cancelled"))
  }

  def count(result: TArray[Option[String]]): ZIO[Any, Nothing, Option[String]] = {
    for {
      countMap <- STM.atomically {
        result.fold(Map.empty[String, Int])((acc, opt) => opt match {
          case Some(v) => acc.updatedWith(v) {
            case Some(exists) => Some(exists + 1)
            case None => Some(1)
          }
          case None => acc
        })
      }
    } yield countMap.maxByOption(_._2).filter(_._2 >= 3).map(_._1)
  }

  def readQuorum(): ZIO[Clock with Random with Logging, String, String] = for {
    p      <- Promise.make[Nothing, String]
    result <- shardInit
    fiber  <- ZIO.foreachPar(shards) { n =>
                   for {
                     v     <- readShard(n)
                     _     <- STM.atomically(result.update(n, _ => Some(v)))
                     reach <- count(result)
                     _     <- reach match {
                                case Some(r) => p.succeed(r)
                                case None => ZIO.unit
                              }
                   } yield ()
                 }
                 .fork
    value  <- p.await
    _      <- fiber.interrupt
  } yield {
    value
  }

}

