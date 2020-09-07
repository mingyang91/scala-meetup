package meetup.compare.zio

import zio.{ExitCode, Task, UIO, URIO}

object ErrorStack extends zio.App {

  def plus8(v: Int): UIO[Int] = UIO { v + 8 }

  def mul5(v: Int): UIO[Int] = UIO { v * 2 }

  def pow3(v: Int): UIO[Int] = UIO { Math.pow(v, 3).toInt }

  def div0(v: Int): Task[Int] = Task { v / 0 }


  override def run(args: List[String]): URIO[zio.ZEnv, ExitCode] = {
    val io = for {
      a1 <- plus8(1)
      a2 <- mul5(a1)
      a3 <- pow3(a2)
      res <- div0(a3)
    } yield res

    io.exitCode
  }

}
