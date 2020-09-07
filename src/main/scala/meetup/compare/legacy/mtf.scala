package meetup.compare.legacy

import meetup.di.legacy.utils.Demo

import scala.concurrent.Future

object mtf extends Demo {

  case class Request(member: Int)
  sealed trait Result
  case class BadRequest(value: Any) extends Result
  case class Unauthorized(value: Any) extends Result
  case class InternalServerError(value: Any) extends Result
  case class NotFound(value: Any) extends Result
  case class Ok(value: Any) extends Result

  val status = ""
  val resourceId = ""
  val resourceType = ""
  val request = Request(0)
  def parseStatus(status: String): Option[Int] = ???

  def checkPermission(resourceId: String, member: Int): Future[Either[String, Unit]] = ???

  def process(resource: Any, status: Any): Future[Either[String, Int]] = ???

  def findResource(id: String): Future[Option[String]] = ???

  parseStatus(status) match {
    case Some(statusValue) =>
      checkPermission(resourceType, request.member)
        .flatMap {
          case Right(()) =>
            findResource(resourceId)
              .flatMap {
                case Some(resource) =>
                  process(resource, statusValue)
                    .map {
                      case Right(result) => Ok(result)
                      case Left(msg) => BadRequest(msg)
                    }
                case None =>
                  Future.successful(NotFound(s"未找到 id 为 $resourceId 的资源"))
              }
          case Left(msg) =>
            Future.successful(Unauthorized(msg))
        }
    case None => BadRequest(s"请求状态($status)不合法")
  }

}
