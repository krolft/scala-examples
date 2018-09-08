trait Monoid[A] {
  def mappend(a: A, b: A): A
  def mzero: A
}

object Monoid {
  implicit val IntMonoid: Monoid[Int] = new Monoid[Int]{
    def mappend(a: Int, b: Int): Int = a + b
    def mzero: Int = 0
  }

  implicit val StringMonoid: Monoid[String] = new Monoid[String]{
    def mappend(a: String, b: String): String = a + b
    def mzero: String = ""
  }
}

object Cats extends App {

  // CONTEXT BOUND
  // - used with 'type class pattern'
  // - emulate haskell type class functionality
  //
  // "[A : Monoid]" = there is an implicit value of type Monoid[A]
  def sum[A : Monoid](xs: List[A]): A = {
    val m = implicitly[Monoid[A]]
    xs.foldLeft(m.mzero)(m.mappend)
  }

  p(sum(List("1", "2", "3")))
  p(sum(List(1, 2, 3)))
  // it's still possible to pass the monoid directly
  p(sum(List("1", "2", "3"))(Monoid.StringMonoid))

  def p(a: Any) = println(">>> " + a)
}


