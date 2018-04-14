package com.knoldus.scalacats.monoids

import cats.Monoid
import cats.instances.int._
import cats.syntax.semigroup._

object Example2 extends App {

  // without monoids
  def addWithoutMonoids(items: List[Int]): Int = {
    items.foldLeft(0)(_ + _)
  }

  // with monoids
  def addWithMonoids(items: List[Int]): Int = {
    items.foldLeft(Monoid[Int].empty)(_ |+| _)
  }

  val resultWithoutMonoid = addWithoutMonoids(List(1, 2, 3, 4, 5, 6))
  val resultWithMonoid = addWithMonoids(List(1, 2, 3, 4, 5, 6))

  println(s"Without Monoids: $resultWithoutMonoid")
  println(s"With Monoids: $resultWithMonoid")
}
