package meetup.compare.zio

import zio.{IO, ZIO}

object mtf {
  case class Request(member: Int)
  sealed trait Result
  case class BadRequest(value: Any) extends Result
  case class Unauthorized(value: Any) extends Result
  case class InternalError(value: Any) extends Result
  case class NotFound(value: Any) extends Result
  case class Ok(value: Any) extends Result

  val statusStr = ""
  val resourceId = ""
  val resourceType = ""
  val request = Request(0)
  def parseStatus(status: String): Option[Int] = ???

  def checkPermission(resourceId: String, member: Int): IO[String, Unit] = ???

  def process(resource: Any, status: Any): IO[String, Int] = ???

  def findResource(id: String): IO[Throwable, Option[String]] = ???

  val io: IO[Result, Result] = for {
    status    <- ZIO.fromOption(parseStatus(statusStr))
                    .orElseFail(BadRequest(s"状态($statusStr)不合法"))

    _         <- checkPermission(resourceType, request.member)
                    .mapError(msg => Unauthorized(msg))

    resource  <- findResource(resourceId)
                   .mapError(err => InternalError(err.getMessage))
                   .someOrFail(NotFound(s"资源 $resourceId 不存在"))

    result    <- process(resource, status)
                    .mapError(msg => BadRequest(msg))
  } yield Ok(result)
}
