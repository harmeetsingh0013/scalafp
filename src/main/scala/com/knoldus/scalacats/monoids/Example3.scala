package com.knoldus.scalacats.monoids

import cats.Monoid
import cats.instances.int._
import cats.instances.option._
import cats.syntax.semigroup._

object Example3 extends App {

  // without monoids
  def addWithoutMonoid(items: List[Option[Int]]): Option[Int] = {
    items.fold(Some(0)){
      case (acc, value) => value match {
        case Some(i) => acc.map(_ + i)
        case None => acc
      }
    }
  }

  // with moinoids
  def addWithMonoid(items: List[Option[Int]]): Option[Int] = {
    items.fold(Monoid[Option[Int]].empty)(_ |+| _)
  }

  val resultWithoutMonoid = addWithoutMonoid(List(Some(1), Some(2), None, None, Some(5), Some(6)))
  val resultWithMonoid = addWithMonoid(List(Some(1), Some(2), None, None, Some(5), Some(6)))

  println(s"Without Monoids: $resultWithoutMonoid")
  println(s"With Monoids: $resultWithMonoid")

  // edge case
  val result = addWithMonoid(List(Some(1), Some(2), Some(3), Some(4), Some(5), Some(6)))
  println(s"Expecting Compile time Error: $result")
}
