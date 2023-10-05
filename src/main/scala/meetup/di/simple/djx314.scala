package meetup.di.simple

import meetup.di.simple.member.{
  Accountant,
  Baker,
  CandyMaker,
  Cashier,
  Decorator,
  HunanChef,
  Logistic,
  Manager,
  Security,
  SichuanChef,
  Waiter,
  Washer
}
import meetup.di.simple.tools.{Mixer, Oven, PipingTip, Turntable}
import meetup.di.simple.utils.Demo

object djx314 extends App with Demo {
  implicit val mixer: Mixer           = Mixer.build
  implicit val oven: Oven             = Oven.build
  implicit val pipingTip: PipingTip   = PipingTip.build
  implicit val turntable: Turntable   = Turntable.build
  implicit val baker: Baker           = Baker.build
  implicit val decorator: Decorator   = Decorator.build
  implicit val candyMaker: CandyMaker = CandyMaker.build
  implicit val scChef: SichuanChef    = SichuanChef.build
  implicit val hnChef: HunanChef      = HunanChef.build
  implicit val cashier: Cashier       = Cashier.build
  implicit val waiter: Waiter         = Waiter.build
  implicit val washer: Washer         = Washer.build
  implicit val logistic: Logistic     = Logistic.build
  implicit val security: Security     = Security.build
  implicit val accountant: Accountant = Accountant.build
  implicit val manager: Manager       = Manager.build
  val cs: CakeShop                    = CakeShop.build

  cs.甜在心蛋糕().onComplete(println)

}
