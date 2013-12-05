package coderpuppy.lib.game

import org.lwjgl.opengl.GL11._

trait Texture {
	def width: Int
	def height: Int
	def bind
	def drawAt(x: Int, y: Int) {
		bind
		
		glBegin(GL_QUADS)
		
		glTexCoord2f(0, 0)
		glVertex2f(x, y)
		glTexCoord2f(1, 0)
		glVertex2f(x + width, y)
		glTexCoord2f(1, 1)
		glVertex2f(x + width, y + height)
		glTexCoord2f(0, 1)
		glVertex2f(x, y + height)
		
		glEnd
	}
}