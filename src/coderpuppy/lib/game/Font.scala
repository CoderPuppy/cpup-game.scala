package coderpuppy.lib.game

import java.awt.{Font => AwtFont}
import org.newdawn.slick.{TrueTypeFont, Font => SlickFont}
import org.newdawn.slick.Color

class Font(val awtFont: AwtFont) {
	val ttf = new TrueTypeFont(awtFont, Font.antiAlias)
	
	def getWidth(text: String) = ttf.getWidth(text)
	def getHeight(text: String) = ttf.getHeight(text)
	
	def draw(x: Int, y: Int, text: String, color: Color) {
		ttf.drawString(x, y, text, color)
	}
}

object Font {
	var antiAlias = true
	
	val plain = AwtFont.PLAIN
	val bold = AwtFont.BOLD
	val italic = AwtFont.ITALIC
	
	def sansSerif(size: Int = 14, style: Int = AwtFont.PLAIN) = new Font(new AwtFont("sans-serif", style, size))
}