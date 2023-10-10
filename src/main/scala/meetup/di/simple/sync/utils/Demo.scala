package meetup.di.simple.sync.utils

import org.apache.pekko.actor.ActorSystem
import org.apache.pekko.pattern.after

import scala.concurrent.duration.{DurationInt, FiniteDuration}
import scala.concurrent.{ExecutionContextExecutor, Future}

trait Demo {

  implicit val system: ActorSystem          = Demo.system
  implicit val ec: ExecutionContextExecutor = system.dispatcher

  def delay(duration: FiniteDuration = 1.seconds): Future[Unit] = after(duration, system.scheduler)(Future.unit)
}

object Demo {
  private val system: ActorSystem = ActorSystem()
}
