package coderpuppy.lib.game.events

case class ResizeEvent(val width: Int, val height: Int) extends Event {}