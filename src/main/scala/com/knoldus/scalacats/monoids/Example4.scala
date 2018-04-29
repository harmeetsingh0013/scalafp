package com.knoldus.scalacats.monoids

import cats.Monoid
import cats.instances.double._
import cats.instances.int._
import cats.syntax.semigroup._

object Example4 extends App {
  case class Order(totalCost: Double, quantity: Int)

  implicit val orderMonoids = new Monoid[Order] {
    override def empty: Order = Order(0.0, 0)

    override def combine(x: Order, y: Order): Order =
      Order((x.totalCost |+| y.totalCost), (x.quantity |+| y.quantity))
  }

  def addOrders(list: List[Order])(implicit M: Monoid[Order]) = {
    list.foldLeft(Monoid.empty[Order]) ( M.combine(_ , _) )
  }

  val orders = List(Order(9, 3), Order(33, 4), Order(34, 3))
  println(s" Total Order are: ${addOrders(orders)}")
}
