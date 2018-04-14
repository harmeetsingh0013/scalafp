package com.knoldus.scalacats.semigroups

object Example1 extends App with Data {

  //add money function
  def add(money1: Money, money2: Money): Money = {
    Money(money1.dollars + money2.dollars + ((money1.cents + money2.cents) / 100),
      (money1.cents + money2.cents) % 100)
  }

  // add salary to account current balance
  def addMoneyMap(balances: Map[String, Money], salary: Map[String, Money]): Map[String, Money] = {
    balances.foldLeft(salaries){
      case (acc, (name, money)) =>
        acc + (name -> acc.get(name).map(add(_ , money)).getOrElse(money))
    }
  }

  // add wining marbel into existing balance
  def addMarbleMap(balances: Map[String, Int], wining: Map[String, Int]): Map[String, Int] = {
    balances.foldLeft(wining) {
      case (acc, (name, marble)) =>
        acc + (name -> acc.get(name).map(_ + marble).getOrElse(marble))
    }
  }

  println(s"Salary credit in you account xxxxxxx ${add(balance, salary)}")
  println(s"Salary transfer to all employees ${addMoneyMap(balances, salaries)}")
  println(s"Your game marbles balance is: ${addMarbleMap(marbles, won)}")
}
