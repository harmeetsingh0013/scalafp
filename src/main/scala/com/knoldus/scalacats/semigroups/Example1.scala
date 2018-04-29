package com.knoldus.scalacats.semigroups

object Example1 extends App with Data {

  def addMoney(a: Money, b: Money): Money = {
    Money((a.dollars + b.dollars) + (a.cents + b.cents) / 100,
      (a.cents + b.cents) % 100)
  }

  def creditSalary(balances: Map[String, Money], salary: Map[String, Money]): Map[String, Money] = {
    balances.foldLeft(salary) {
      case (acc, (name, money)) =>
        acc + (name -> acc.get(name).fold(money)(addMoney(_, money)))
    }
  }

  def addMarbles(balance: Map[String, Int], wining: Map[String, Int]): Map[String, Int] = {
    balance.foldLeft(wining) {
      case (acc, (name, marble)) =>
        acc + (name -> acc.get(name).fold(marble)(_ + marble))
    }
  }

  println(s" Credit salary to you account ${creditSalary(balances, salaries)}")
  println(s" Add marbles tou your game account ${addMarbles(marbles, won)}")
}
