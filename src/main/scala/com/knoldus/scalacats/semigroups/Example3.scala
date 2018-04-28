package com.knoldus.scalacats.semigroups

object Example3 extends App with Data {

  trait Addable [T] {
    def add(a: T, b: T): T
  }

  implicit val addMoney = new Addable[Money] {
    override def add(a: Money, b: Money): Money =  Money((a.dollars + b.dollars) + (a.cents + b.cents) / 100,
      (a.cents + b.cents) % 100)
  }

  implicit val addInt = new Addable[Int] {
    override def add(a: Int, b: Int): Int = a + b
  }

  implicit def addMap[K, V: Addable] = new Addable[Map[K, V]]{
    override def add(a: Map[K, V], b: Map[K, V]): Map[K, V] = {
      a.foldLeft(b) {
        case (acc, (k, v)) =>
          acc + (k -> acc.get(k).fold(v)(implicitly[Addable[V]].add(_, v)))
      }
    }
  }

  def add[A: Addable](a: A, b: A)(implicit addable: Addable[A]): A = addable.add(a, b)

  println(s" Salary credit into you account ${add(balances, salaries)}")
  println(s" Add marbles to you game account ${add(marbles, won)}")
  println(s" Add money onlye: ${add(Money(10, 4), Money(98, 4))}")
}
