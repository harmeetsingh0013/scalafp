package com.knoldus.scalacats.monoids

import cats.Monoid
import cats.instances.double._
import cats.syntax.semigroup._

object Example4 extends App {

  implicit val orderMonoid = new Monoid[Order] {
    override def empty: Order = Order(0, 0)

    override def combine(x: Order, y: Order): Order = {
      Order((x.totalCost |+| y.totalCost), (x.quantity |+| y.quantity))
    }
  }
  case class Order(totalCost: Double, quantity: Double)

  def addOrders(orders: List[Order])(implicit m: Monoid[Order]): Order = {
    orders.foldRight(m.empty){
      case (acc, value) => m.combine(acc, value)
    }
  }

  val result = addOrders(List(Order(2, 3), Order(5, 3), Order(22, 45)))
  println(s" You total bill is: $result")
}
