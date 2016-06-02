package react.impls.helper

import react.ReactiveLibrary
import react.ReactiveLibrary.{EventSourceCompanionObject, ConstCompanionObject, Cancelable}


object NonCancelable extends Cancelable {
  override def kill(): Unit = {}

  import language.implicitConversions
  implicit def unitToCancelable(x: Unit): Cancelable = NonCancelable
}

trait DefaultConstObject {
  self: ReactiveLibrary =>

  final object Const extends ConstCompanionObject[Signal] {
    override def apply[A](value: A): Var[A] = Var(value)
  }
}

trait ReactiveLibraryImplementationHelper {
  self: ReactiveLibrary =>
  override protected type VolatileHelper = Any

  trait EventSourceCompanionObjectImplementationHelper extends EventSourceCompanionObject[Event, EventSource] {
    def Never: Event[Nothing] = apply[Nothing]
  }

}