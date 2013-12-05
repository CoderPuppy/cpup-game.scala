package coderpuppy.lib.game.events

import org.lwjgl.input.Mouse
import coderpuppy.lib.game.Graphics

case class MouseScrollEvent(val scroll: Int = Mouse.getEventDWheel, val pos: (Int, Int) = (Mouse.getEventX, Graphics.height - Mouse.getEventY), val timestamp: Long = Mouse.getEventNanoseconds) extends MouseEvent {}