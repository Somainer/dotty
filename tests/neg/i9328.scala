// fuzz test to avoid NoSymbol.owner
type Id[T] = T match {
  case _ => T
}

class Foo2[T <: Id[T]] // error // error

object Foo { // error
  object Foo { }
  Foo { }
}
implicit class Foo(a: Float)  // error
case class Foo()

case class Bar(
} { ;  // error
object Bar { // error
  class Foo(a: Int) extends AnyVal
  Foo()
}
type Bar // error