package coderpuppy.lib.game

import org.lwjgl.input.Keyboard
import org.lwjgl.input.Mouse
import coderpuppy.lib.game.events.EventHandler
import coderpuppy.lib.game.events.Event
import coderpuppy.lib.game.events.KeyEvent
import coderpuppy.lib.game.events.MouseBtnEvent
import coderpuppy.lib.game.events.MouseMoveEvent
import coderpuppy.lib.game.events.MouseScrollEvent
import coderpuppy.lib.game.events.MouseClickEvent

object Input {
	var handler: EventHandler = null
	
	protected var oldX: Int = 0
	protected var oldY: Int = 0
	
	protected var mouseBtns: Array[Boolean] = null
	
	def init {
		Keyboard.create
		Mouse.create
		
		mouseBtns = Array.fill[Boolean](Mouse.getButtonCount)(false)
	}
	
	def update {
		while(Keyboard.next) {
			emitEvent(KeyEvent())
		}
		
		while(Mouse.next) {
			var button = Mouse.getEventButton
			var state = Mouse.getEventButtonState
			
			if(button != -1) {
				emitEvent(MouseBtnEvent())
				
				if(!state && mouseBtns(button)) {
					emitEvent(MouseClickEvent())
				}
				
				mouseBtns(button) = state
			}
			
			if(Mouse.getEventX != oldX || Mouse.getEventY != oldY) {
				oldX = Mouse.getEventX
				oldY = Mouse.getEventY
				emitEvent(MouseMoveEvent())
			}
			
			if(Mouse.getEventDWheel > 0) {
				emitEvent(MouseScrollEvent())
			}
		}
	}
	
	def destroy {
		Mouse.destroy
		Keyboard.destroy
	}
	
	protected def emitEvent(e: Event) {
		if(handler != null) {
			handler.onEvent(e)
		}
	}
}