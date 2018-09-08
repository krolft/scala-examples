# scala examples

## learnings from scalaz

https://vimeo.com/10482466

## Ad-hoc Polymorphism

vs. just/normal/regular Polymorphism

```scala
def headOption[T](xs: Seq[T]): Option[T]
```
here the method is defined for all possible T

Ad-hoc imposes a _restriction_!
```scala
def show[T](t: T): String
```
Not all T can be transformed to String. E.g. Int

Usually you'd do something like:
```scala
trait Show[T] {
  def transform(t: T): String
}
def show[T <: Show[T]](t: T): String
```

In scalaz it is done differently.
Still Traits but _no inheritance_.
It uses implicits and implicit conversions.

See `cats.scala`

