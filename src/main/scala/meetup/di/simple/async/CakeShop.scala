package meetup.di.simple.async

import meetup.di.simple.async.member._
import meetup.di.simple.async.tools.{Mixer, Oven, PipingTip, Turntable}
import zio.{Has, ZIO}
import zio.clock.Clock
import zio.logging.Logging

class CakeShop(
  cashier: Cashier,
  waiter: Waiter,
  washer: Washer,
  logistic: Logistic,
  security: Security,
  accountant: Accountant,
  manager: Manager,
  baker: Baker,
  decorator: Decorator,
  candyMaker: CandyMaker,
  sichuanChef: SichuanChef,
  hunanChef: HunanChef
) {

  def opening = {
    cashier.standby() *>
      waiter.onCall() *>
      washer.washing() *>
      logistic.supplement() *>
      security.patrol() *>
      accountant.bookkeeping() *>
      manager.scheduling()
  }

  ///
  /// 2001 年新增菜品
  ///

  def 甜在心蛋糕(): ZIO[Logging with Clock with Has[PipingTip] with Has[Turntable] with Has[Mixer] with Has[Oven], Nothing, String] = {
    for {
      base      <- baker.makeCakeBase()
      decorated <- decorator.decorating(base)
    } yield s"被 $decorated 装饰的 甜在心 $base"
  }

  ///
  /// 2002 年新增菜品
  ///

  def 慕斯(): ZIO[Logging with Clock with Has[Oven] with Has[Mixer], Nothing, String] = {
    for {
      base    <- baker.makeCakeBase()
      covered <- candyMaker.maskChocolate(base)
    } yield s"$covered$base"
  }

  ///
  /// 2003 年新增菜品
  ///

  def 马卡龙() = ???

  ///
  /// 2004 年新增菜品
  ///

  def 水果拼盘蛋糕(): ZIO[Logging with Clock with Has[Oven] with Has[Mixer], Nothing, String] = {
    for {
      base    <- baker.makeCakeBase()
      covered <- candyMaker.maskFruit(base)
    } yield s"$covered$base"
  }

  ///
  /// 2015 年
  ///
  def 小龙虾蛋糕(): ZIO[Logging with Clock with Has[Oven] with Has[Mixer], Nothing, String] = {
    for {
      base     <- baker.makeCakeBase()
      toppings <- sichuanChef.cooking("小龙虾")
    } yield toppings + base
  }

  ///
  /// 2016 年新增菜品
  ///

  def 臭豆腐蛋糕(): ZIO[Logging with Clock with Has[Oven] with Has[Mixer], Nothing, String] = {
    for {
      base     <- baker.makeCakeBase()
      toppings <- hunanChef.cooking("臭豆腐")
    } yield toppings + base
  }

  ///
  /// 其他幺蛾子
  ///

}

object CakeShop {
  def build(implicit
    cashier: Cashier,
    waiter: Waiter,
    washer: Washer,
    logistic: Logistic,
    security: Security,
    accountant: Accountant,
    manager: Manager,
    baker: Baker,
    decorator: Decorator,
    candyMaker: CandyMaker,
    sichuanChef: SichuanChef,
    hunanChef: HunanChef
  ): CakeShop = new CakeShop(
    implicitly,
    implicitly,
    implicitly,
    implicitly,
    implicitly,
    implicitly,
    implicitly,
    implicitly,
    implicitly,
    implicitly,
    implicitly,
    implicitly
  )
}
