package coderpuppy.lib.game.gui

import coderpuppy.lib.game.Graphics
import org.newdawn.slick.Color
import org.newdawn.slick.TrueTypeFont
import coderpuppy.lib.game.Font

class GUILabel(var text: String) extends GUI {
	var font = Font.sansSerif()
	var color = Color.red
	
	var x = 0
	var y = 0
	
	def width = font.getWidth(text)
	def height = font.getHeight(text)
	
	def render {
		Graphics.text(x, y, text, font, color)
	}
}