package com.knoldus.scalacats.semigroups

object Example2 extends App with Data {

  def addM(a: Money, b: Money): Money = {
    Money((a.dollars + b.dollars) + (a.cents + b.cents) / 100,
      (a.cents + b.cents) % 100)
  }

  trait Addable [T] {
    def add(a: T, b: T): T
  }

  def addMap[K, V: Addable](balances: Map[K, V], map: Map[K, V])(implicit addable: Addable[V]): Map[K, V] = {
    balances.foldLeft(map) {
      case (acc, (k, v)) =>
        acc + (k -> acc.get(k).fold(v)(addable.add(_, v)))
    }
  }

  implicit val addMoney = new Addable[Money] {
    override def add(a: Money, b: Money): Money = addM(a, b)
  }

  implicit val addInt = new Addable[Int] {
    override def add(a: Int, b: Int): Int = a + b
  }

  println(s" Salary credit into you account ${addMap(balances, salaries)}")
  println(s" Add marbles to you game account ${addMap(marbles, won)}")
}
