package com.knoldus.scalacats.monoids.usecases

import cats.kernel.Monoid
import com.knoldus.scalacats.semigroups.{Data, Money}

object MoneyExample extends App with Data {

  implicit val moneyMonoid = new Monoid[Money] {
    override def empty: Money = Money(0, 0)

    override def combine(x: Money, y: Money): Money = {
      Money(x.dollars + y.dollars + ((x.cents + y.cents) / 100),
        (x.cents + y.cents) % 100)
    }
  }

  val lastYearExpenses = List(Money(3, 4), Money(34, 5), Money(12, 0))

  def totalAllExpenses(expenses: List[Money])(implicit m: Monoid[Money]): Money = {
    expenses.foldLeft(m.empty){
      case (acc, money) => m.combine(acc, money)
    }
  }

  println(s"LastYearExpenses : ${totalAllExpenses(lastYearExpenses)}")
}
