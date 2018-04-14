package com.knoldus.scalacats.semigroups

object Example3 extends App with Data {

  trait Addable[T] {
    def add(a: T, b: T): T
  }

  implicit val addInt = new Addable[Int] {
    override def add(a: Int, b: Int): Int = a + b
  }

  implicit val addMoney = new Addable[Money] {
    override def add(a: Money, b: Money): Money = {
      Money(a.dollars + b.dollars + ((a.cents + b.cents) / 100),
        (a.cents + b.cents) % 100)
    }
  }

  // [V: Addable] is shothand of (implicit addable: Addable[A])
  implicit def addMap[K, V: Addable] = new Addable[Map[K, V]] {
    override def add(a: Map[K, V], b: Map[K, V]): Map[K, V] = {
      a.foldLeft(b) {
        case (acc, (x, y)) =>
          acc + (x -> acc.get(x).map(implicitly[Addable[V]].add(_, y)).getOrElse(y))
      }
    }
  }

  def add[A: Addable](a: A, b: A)(implicit addable: Addable[A]): A = addable.add(a, b)

  println(s"Salary credit in you account xxxxxxx ${add(balance, salary)}")
  println(s"Salary transfer to all employees ${add(balances, salaries)}")
  println(s"Your game marbles balance is: ${add(marbles, won)}")
}
