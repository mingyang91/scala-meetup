package schedule.queue
import izumi.reflect.Tag
import zio.blocking.Blocking
import zio.duration.durationInt
import zio.logging.Logging
import zio._

object Run extends scala.App {

  val logger = Logging.console(rootLoggerName = Some(this.getClass.getName))
  val io = for {
    _ <- Logging.info("start")
    fetch = for {
      _    <- Logging.info("fetching")
      head <- ZQueue.head[Any, Nothing, Int]().retry(Schedule.spaced(100.milliseconds))
      res  <- head
      _    <- Logging.info("fetch" + res.toString)
    } yield ()

    _ <- fetch.forever
  } yield ()

  val provided = io.provideSomeLayer[zio.ZEnv](logger ++ ZQueue.live[Any, Nothing, Int])
  zio.Runtime.default.unsafeRun(provided)
}

class ZQueue[R, E, A](private val underling: scala.collection.mutable.Queue[ZIO[R, E, A]]) {
  for {
    i <- 1 to 10000
  } underling.enqueue(ZIO.succeed(i).asInstanceOf[ZIO[R, E, A]])
}

object ZQueue {
  def live[R, E, A](implicit ev0: Tag[R], ev1: Tag[E], ev2: Tag[A]): ZLayer[Any, Nothing, Has[ZQueue[R, E, A]]] = make[R, E, A]().toLayer

  def make[R, E, A](): UIO[ZQueue[R, E, A]] = UIO.effectTotal { new ZQueue[R, E, A](scala.collection.mutable.Queue.empty) }

  def offer[R, E, A](io: ZIO[R, E, A])(implicit ev0: Tag[R], ev1: Tag[E], ev2: Tag[A]): ZIO[Has[ZQueue[R, E, A]], Throwable, Unit] = for {
    queue <- ZIO.service[ZQueue[R, E, A]]
    _     <- ZIO.effect(queue.underling += io)
  } yield ()

  def head[R, E, A]()(implicit
    ev0: Tag[R],
    ev1: Tag[E],
    ev2: Tag[A]
  ): ZIO[Blocking with Has[ZQueue[R, E, A]], Option[Nothing], ZIO[R, E, A]] = {
    val io = for {
      queue <- ZIO.service[ZQueue[R, E, A]]
      value <- ZIO.effect { queue.underling.dequeue() }
    } yield value

    io.orElseFail(None)
  }
}
