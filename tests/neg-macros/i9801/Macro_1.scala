import scala.quoted.*

def f() = ()

def triggerStackOverflow(n: Int): Expr[Double] = {
  val r = triggerStackOverflow(n - 1)
  f()
  r
}

inline def loop(inline prog: Double): Double = ${impl('prog)}

def impl(prog: Expr[Double])(using Quotes) : Expr[Double] =
  import quotes.reflect.*
  try {
    triggerStackOverflow(0)
  } catch {
    case e =>
      quotes.reflect.report.error(e.getMessage, prog.asTerm.pos)
      '{ 42.0 }
  }
