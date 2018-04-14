package com.knoldus.scalacats.semigroups

object Example2 extends App with Data {

  //add money function
  def addM(money1: Money, money2: Money): Money = {
    Money(money1.dollars + money2.dollars + ((money1.cents + money2.cents) / 100),
      (money1.cents + money2.cents) % 100)
  }

  trait Addable[T] {
    def add(a: T, b: T): T
  }

  def add[X, Y](balances: Map[X, Y], addMap: Map[X, Y])
               (implicit addable: Addable[Y]): Map[X, Y] = {
    balances.foldLeft(addMap) {
      case (acc, (x, y)) =>
        acc + (x -> acc.get(x).map(addable.add(_, y)).getOrElse(y))
    }
  }

  println(s"Salary credit in you account xxxxxxx ${addM(balance, salary)}")

  implicit val addMoney = new Addable[Money] {
    override def add(a: Money, b: Money): Money = addM(a, b)
  }
  println(s"Salary transfer to all employees ${add(balances, salaries)}")

  implicit val addInt = new Addable[Int] {
    override def add(a: Int, b: Int): Int = a + b
  }
  println(s"Your game marbles balance is: ${add(marbles, won)}")
}
