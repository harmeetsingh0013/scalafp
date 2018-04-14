package com.knoldus.scalacats.monoids.usecases

import cats.kernel.Monoid

/*
// Let's have a popularity contest on Twitter.  The user with the most followers wins!
val harmeetsingh = TwitterUser("singh_harmeet13", 132)
val knoldus = TwitterUser("knolspeak", 575)
val vikas = TwitterUser("vhazrati", 387)
val dzone = TwitterUser("dzone", 10640)
val scala = TwitterUser("scala_lang", 20421)

val winner: Max[TwitterUser] = Max(barackobama) + Max(katyperry) + Max(ladygaga) + Max(miguno) + Max(taylorswift)
assert(winner.get == katyperry)

*/

object TwitterAlgebirds extends App {

  case class TwitterUser(username: String, followers: Int) extends Ordered[TwitterUser] {
    override def compare(that: TwitterUser): Int = {
      val c = this.followers - that.followers
      if(c == 0) this.username.compareTo(that.username) else c
    }
  }

  implicit val twitterUserMonoid = new Monoid[TwitterUser] {
    override def empty: TwitterUser = TwitterUser("MinUser", Int.MinValue)

    override def combine(x: TwitterUser, y: TwitterUser): TwitterUser = {
      if(x.compareTo(y) >= 1) x else y
    }
  }

  case class Max(user: TwitterUser) {
    def +(usr: Max)(implicit m: Monoid[TwitterUser]): Max = {
      Max(m.combine(this.user, usr.user))
    }
  }

  val harmeetsingh = TwitterUser("singh_harmeet13", 132)
  val knoldus = TwitterUser("knolspeak", 575)
  val vikas = TwitterUser("vhazrati", 387)
  val dzone = TwitterUser("dzone", 10640)
  val scala = TwitterUser("scala_lang", 20421)

  val winner: Max = Max(harmeetsingh) + Max(knoldus) + Max(vikas) + Max(dzone) + Max(scala)

  println(s"Winner Is: $winner")
  assert(winner.user == scala)
}
