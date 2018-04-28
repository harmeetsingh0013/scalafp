package com.knoldus.scalacats.semigroups

object Example1 extends App with Data {

  def add(a: Money, b: Money): Money = {
    Money((a.dollars + b.dollars) + (a.cents + b.cents) / 100,
      (a.cents + b.cents) % 100)
  }

  def creditSalary(balances: Map[String, Money], salary: Map[String, Money]): Map[String, Money] = {
    balances.foldLeft(salary) {
      case (acc, (name, money)) =>
        acc + (name -> acc.get(name).fold(money)(add(_, money)))
    }
  }

  def addMarbles(balances: Map[String, Int], winning: Map[String, Int]): Map[String, Int] = {
    balances.foldLeft(winning) {
      case (acc, (name, marbels)) =>
        acc + (name -> acc.get(name).fold(marbels)(_ + marbels))
    }
  }

  println(s" Salary credit into you account ${creditSalary(balances, salaries)}")
  println(s" Add marbles to you game account ${addMarbles(marbles, won)}")
}
