package coderpuppy.lib.game.gui

import coderpuppy.lib.game.events.Event
import coderpuppy.lib.game.events.EventHandler
import coderpuppy.lib.game.events.KeyEvent
import coderpuppy.lib.game.events.ResizeEvent
import coderpuppy.lib.game.events.MouseMoveEvent
import coderpuppy.lib.game.events.MouseBtnEvent
import coderpuppy.lib.game.events.MouseScrollEvent
import coderpuppy.lib.game.events.MouseClickEvent
import coderpuppy.lib.game.events.MouseEvent

abstract class GUI extends EventHandler {
	def x: Int
	def y: Int
	def width: Int
	def height: Int
	
	def update {}
	def render
	def onEvent(e: Event): Boolean = e match {
		case e: KeyEvent => onKey(e)
		case e: MouseEvent => {
			if(e.pos._1 >= x && e.pos._1 <= x + width && e.pos._2 >= y && e.pos._2 <= y + height) {
				e match {
					case e: MouseClickEvent => onClick(e)
					case e: MouseBtnEvent => onMouseBtn(e)
					case e: MouseMoveEvent => onMouseMove(e)
					case e: MouseScrollEvent => onScroll(e)
				}
			} else {
				false
			}
		}
		case e: ResizeEvent => onResize(e)
	}
	
	def onMouseBtn(e: MouseBtnEvent): Boolean = false
	def onMouseMove(e: MouseMoveEvent): Boolean = false
	def onScroll(e: MouseScrollEvent): Boolean = false
	def onClick(e: MouseClickEvent): Boolean = false
	def onKey(e: KeyEvent): Boolean = false
	
	// TODO: Trigger this automagically
	def onResize(e: ResizeEvent): Boolean = false
}