package com.knoldus.scalacats.monoids.usecases

import cats.kernel.Monoid

/*
// Let's have a popularity contest on Twitter.  The user with the most followers wins!
val harmeetsingh = TwitterUser("singh_harmeet13", 132)
val knoldus = TwitterUser("knolspeak", 575)
val vikas = TwitterUser("vhazrati", 387)
val dzone = TwitterUser("dzone", 10640)
val scala = TwitterUser("scala_lang", 20421)

val winner: Max = Max(harmeetsingh) + Max(knoldus) + Max(vikas) + Max(dzone) + Max(scala)
assert(winner.twitterUser == scala)

*/

object TwitterAlgebirds extends App {

  case class TwitterUser(name: String, followers: Int) extends Ordered[TwitterUser] {
    override def compare(that: TwitterUser): Int = {
      val count = this.followers - that.followers
      if(count == 0) this.name.compareTo(that.name) else count
    }
  }

  implicit val twitterUserMonoid = new Monoid[TwitterUser] {
    override def empty: TwitterUser = TwitterUser("MinUsers", Int.MinValue)

    override def combine(x: TwitterUser, y: TwitterUser): TwitterUser = {
      if(x >= y) x else y
    }
  }

  case class Max(twitterUser: TwitterUser) {
    def +(max: Max)(implicit M: Monoid[TwitterUser]): Max = {
      Max(M.combine(this.twitterUser, max.twitterUser))
    }
  }

  val harmeetsingh = TwitterUser("singh_harmeet13", 132)
  val knoldus = TwitterUser("knolspeak", 575)
  val vikas = TwitterUser("vhazrati", 387)
  val dzone = TwitterUser("dzone", 10640)
  val scala = TwitterUser("scala_lang", 20421)

  val winner: Max = Max(harmeetsingh) + Max(knoldus) + Max(vikas) + Max(dzone) + Max(scala)

  println(s" Winner: ${winner}")
  assert(winner.twitterUser == scala)
}
