package meetup.di.legacy

import meetup.di.legacy.member.{Accountant, Baker, CandyMaker, Cashier, Decorator, HunanChef, Logistic, Manager, Security, SichuanChef, Waiter, Washer}
import meetup.di.legacy.tools.{Mixer, Oven, PipingTip, Turntable}
import meetup.di.legacy.utils.Demo

object djx314 extends App with Demo {
  val mixer = new Mixer()
  val oven = new Oven()
  val pipingTip = new PipingTip()
  val turntable = new Turntable()
  val baker = new Baker(oven, mixer)
  val decorator = new Decorator(mixer, pipingTip, turntable)
  val candyMaker = new CandyMaker
  val scChef = new SichuanChef
  val hnChef = new HunanChef
  val cashier = new Cashier
  val waiter = new Waiter
  val washer = new Washer
  val logistic = new Logistic
  val security = new Security
  val accountant = new Accountant
  val manager = new Manager
  val cs = new CakeShop(
    baker, decorator, candyMaker,
    scChef, hnChef, cashier,
    waiter, washer, logistic,
    security, accountant, manager
  )

  cs.甜在心蛋糕()
    .onComplete(println)

}
