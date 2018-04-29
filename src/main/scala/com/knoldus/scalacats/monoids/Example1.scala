package com.knoldus.scalacats.monoids

object Example1 extends App {

  trait Monoid[A] {
    def combine(a: A, b: A): A
    def empty: A
  }

  def associativeLaw[A](x: A, y: A, z: A)(implicit monoid: Monoid[A]): Boolean = {
    monoid.combine(x, monoid.combine(y, z)) == monoid.combine(monoid.combine(x, y), z)
  }

  def identityLaw[A](x: A)(implicit monoid: Monoid[A]) : Boolean = {
    (monoid.combine(x, monoid.empty) == x) && (monoid.combine(monoid.empty, x) == x)
  }

  implicit val intMonoid = new Monoid[Int] {
    override def combine(a: Int, b: Int): Int = a + b

    override def empty: Int = 0
  }

  val result1 = associativeLaw(2, 4, 5)
  val result2 = identityLaw(4)

  assert(result1 == true)
  assert(result2 == true)
}
