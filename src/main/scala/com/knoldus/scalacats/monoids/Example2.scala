package com.knoldus.scalacats.monoids

import cats.Monoid
import cats.instances.int._
import cats.syntax.semigroup._

object Example2 extends App {

  def addWithoutMonoid(list: List[Int]): Int = {
    list.foldLeft(0) (_ + _)
  }

  def addWithMonoid(list: List[Int]): Int = {
    list.foldLeft(Monoid.empty[Int]) (_ |+| _)
  }

  val result1 = addWithoutMonoid(List(1, 3, 4, 5, 6, 7, 8, 6))
  val result2 = addWithMonoid(List(1, 3, 4, 5, 6, 7, 8, 6))

  println(s" Reuslt is: ${result1}")
  assert(result1 == result2)
}
