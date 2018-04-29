package com.knoldus.scalacats.semigroups

import cats.Semigroup

object Example4 extends App with Data {

  implicit val addMoney = new Semigroup[Money] {
    override def combine(a: Money, b: Money): Money =
      Money((a.dollars + b.dollars) + (a.cents + b.cents) / 100,
        (a.cents + b.cents) % 100)
  }

  import cats.instances.int._
  import cats.instances.map._

  def add[A: Semigroup](a: A, b: A)(implicit semigroup: Semigroup[A]): A = semigroup.combine(a, b)

  println(s" Credit salary to you account ${add(balances, salaries)}")
  println(s" Add marbles tou your game account ${add(marbles, won)}" )

  import cats.syntax.semigroup._

  println(s" Credit salary to you account New Syntax ${balances |+| salaries}")
  println(s" Add marbles tou your game account new Syntax ${marbles |+| won}" )
}

