package meetup.di.zio

import meetup.di.zio.member._
import meetup.di.zio.tools.{Mixer, Oven, PipingTip, Turntable}
import zio.{Has, ZIO}
import zio.clock.Clock
import zio.logging.Logging

object CakeShop {

  def opening = {
    Cashier.standby() *>
      Waiter.onCall() *>
      Washer.washing() *>
      Logistic.supplement() *>
      Security.patrol() *>
      Accountant.bookkeeping() *>
      Manager.scheduling()
  }

  ///
  /// 2001 年新增菜品
  ///

  def 甜在心蛋糕(): ZIO[Logging with Clock with Has[PipingTip] with Has[Turntable] with Has[Mixer] with Has[Oven], Nothing, String] = {
    for {
      base <- Baker.makeCakeBase()
      decorated <- Decorator.decorating(base)
    } yield s"被 $decorated 装饰的 甜在心 $base"
  }


  ///
  /// 2002 年新增菜品
  ///

  def 慕斯(): ZIO[Logging with Clock with Has[Oven] with Has[Mixer], Nothing, String] = {
    for {
      base <- Baker.makeCakeBase()
      covered <- CandyMaker.maskChocolate(base)
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
      base <- Baker.makeCakeBase()
      covered <- CandyMaker.maskFruit(base)
    } yield s"$covered$base"
  }


  ///
  /// 2015 年
  ///
  def 小龙虾蛋糕(): ZIO[Logging with Clock with Has[Oven] with Has[Mixer], Nothing, String] = {
    for {
      base <- Baker.makeCakeBase()
      toppings <- SichuanChef.cooking("小龙虾")
    } yield toppings + base
  }

  ///
  /// 2016 年新增菜品
  ///

  def 臭豆腐蛋糕(): ZIO[Logging with Clock with Has[Oven] with Has[Mixer], Nothing, String] = {
    for {
      base <- Baker.makeCakeBase()
      toppings <- HunanChef.cooking("臭豆腐")
    } yield toppings + base
  }

  ///
  /// 其他幺蛾子
  ///

}
