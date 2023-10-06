package meetup.di.simple

import meetup.di.simple.sync.member._
import meetup.di.simple.sync.utils.Demo
import org.slf4j.LoggerFactory

import scala.concurrent.Future

class CakeShop(
  baker: Baker,
  decorator: Decorator,
  candyMaker: CandyMaker,
  scChef: SichuanChef,
  hnChef: HunanChef,
  cashier: Cashier,
  waiter: Waiter,
  washer: Washer,
  logistic: Logistic,
  security: Security,
  accountant: Accountant,
  manager: Manager
) extends Demo {

  val logger = LoggerFactory.getLogger(this.getClass)

  cashier.standby()
  waiter.onCall()
  washer.washing()
  logistic.supplement()
  security.patrol()
  accountant.bookkeeping()
  manager.scheduling()

  ///
  /// 2001 年新增菜品
  ///

  def 甜在心蛋糕(): Future[String] = {
    for {
      base      <- baker.makeCakeBase()
      decorated <- decorator.decorating(base)
    } yield s"$decorated 甜在心 $base"
  }

  ///
  /// 2002 年新增菜品
  ///

  def 慕斯(): Future[String] = {
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

  def 水果拼盘蛋糕() = {
    for {
      base    <- baker.makeCakeBase()
      covered <- candyMaker.maskFruit(base)
    } yield s"$covered$base"
  }

  ///
  /// 2015 年
  ///
  def 小龙虾蛋糕() = {
    for {
      base     <- baker.makeCakeBase()
      toppings <- scChef.cooking("小龙虾")
    } yield toppings + base
  }

  ///
  /// 2016 年新增菜品
  ///

  def 臭豆腐蛋糕() = {
    for {
      base     <- baker.makeCakeBase()
      toppings <- hnChef.cooking("臭豆腐")
    } yield toppings + base
  }

  ///
  /// 其他幺蛾子
  ///
}

object CakeShop {
  def build(implicit
    baker: Baker,
    decorator: Decorator,
    candyMaker: CandyMaker,
    scChef: SichuanChef,
    hnChef: HunanChef,
    cashier: Cashier,
    waiter: Waiter,
    washer: Washer,
    logistic: Logistic,
    security: Security,
    accountant: Accountant,
    manager: Manager
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
