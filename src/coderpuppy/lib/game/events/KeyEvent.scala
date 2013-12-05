package coderpuppy.lib.game.events

import org.lwjgl.input.Keyboard

case class KeyEvent(val state: Boolean = Keyboard.getEventKeyState, val key: Int = Keyboard.getEventKey, val timestamp: Long = Keyboard.getEventNanoseconds) extends InputEvent {}