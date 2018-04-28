package com.knoldus.scalacats.semigroups

import cats.Semigroup

object Example4 extends App with Data {

  implicit val addMoney = new Semigroup[Money] {
    override def combine(a: Money, b: Money): Money = Money((a.dollars + b.dollars) + (a.cents + b.cents) / 100,
      (a.cents + b.cents) % 100)
  }

  import cats.instances.int._
  import cats.instances.map._

  def add[A: Semigroup](a: A, b: A)(implicit semigroup: Semigroup[A]): A = semigroup.combine(a, b)

  println(s" Salary credit into you account ${add(balances, salaries)}")
  println(s" Add marbles to you game account ${add(marbles, won)}")
  println(s" Add money onlye: ${add(Money(10, 4), Money(98, 4))}")

  import cats.syntax.semigroup._

  println(s" Salary credit into you account ${balances |+| salaries}")
  println(s" Add marbles to you game account ${marbles |+| won}")
}

