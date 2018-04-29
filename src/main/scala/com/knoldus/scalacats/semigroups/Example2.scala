package com.knoldus.scalacats.semigroups

object Example2 extends App with Data {

  def addM(a: Money, b: Money): Money = {
    Money((a.dollars + b.dollars) + (a.cents + b.cents) / 100,
      (a.cents + b.cents) % 100)
  }

  trait Addable [T] {
    def add(a: T, b: T): T
  }

  def addMaps[K, V](balance: Map[K, V], newMap: Map[K, V])(implicit addable: Addable[V]): Map[K, V] = {
    balance.foldLeft(newMap) {
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

  println(s" Credit salary to you account ${addMaps(balances, salaries)}")
  println(s" Add marbles tou your game account ${addMaps(marbles, won)}")
}
