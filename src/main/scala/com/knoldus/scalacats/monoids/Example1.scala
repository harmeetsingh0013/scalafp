package com.knoldus.scalacats.monoids

object Example1 extends App {

  trait Monoid[A] {
    def combine(x: A, y: A): A
    def empty: A
  }

  def associativeLaw[A](x: A, y: A, z: A)(implicit M: Monoid[A]): Boolean = {
    M.combine(x, M.combine(y, z)) == M.combine(M.combine(x, y), z)
  }

  def identifyLaw[A](x: A)(implicit M: Monoid[A]): Boolean = {
    (M.combine(x, M.empty) == x) && (M.combine(M.empty, x) == x)
  }

  implicit val implicitMonoid = new Monoid[Int] {
    override def combine(x: Int, y: Int): Int = x + y

    override def empty: Int = 0
  }

  val assResult = associativeLaw(2, 3, 5)
  val identResult = identifyLaw(4)

  println(s"Associative Law: $assResult")
  println(s"Identify Law: $identResult")
}
