package coderpuppy.lib.game

import coderpuppy.lib.game.events.ResizeEvent
import org.lwjgl.opengl.Display
import coderpuppy.lib.game.events.Event
import coderpuppy.lib.game.events.EventHandler
import org.newdawn.slick.Color
import org.lwjgl.opengl.DisplayMode
import org.lwjgl.opengl.GL11._

object Graphics {
	var handler: EventHandler = null
	
	protected var _width = 800
	protected var _height = 600
	
	def width = _width
	def height = _height
	
	def running = !Display.isCloseRequested
	
	def init(w: Int, h: Int) {
		_width = w
		_height = h
		init
	}
	
	def init {
		Display.setResizable(true)
		Display.setDisplayMode(new DisplayMode(_width, _height))
		Display.create
		
		glDisable(GL_DEPTH_TEST)
		
		glEnable(GL_BLEND)
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA)
		
		glClearColor(0, 0, 0, 0)
		
		initOrtho
	}
	
	def initOrtho {
		glViewport(0, 0, _width, _height)
		glMatrixMode(GL_PROJECTION)
		glLoadIdentity
		glOrtho(0, _width, _height, 0, 1, -1)
		glMatrixMode(GL_MODELVIEW)
	}
	
	def update(fn: () => Unit) {
		if(Display.wasResized) {
			_width = Display.getWidth
			_height = Display.getHeight
			initOrtho
			emitEvent(ResizeEvent(_width, _height))
		}
		
		glClear(GL_COLOR_BUFFER_BIT)
		
		fn()
		
		Display.update
	}
	
	def sync(fps: Int) {
		Display.sync(fps)
	}
	
	def destroy {
		Display.destroy
	}
	
	protected def emitEvent(e: Event) {
		if(handler != null) {
			handler.onEvent(e)
		}
	}
	
	var currentColor: Color = Color.black
	
	def text(x: Int, y: Int, text: String, font: Font = Font.sansSerif(), color: Color = currentColor) {
		font.draw(x, y, text, color)
	}
}