package coderpuppy.lib.game

import coderpuppy.lib.game.gui.GUI

abstract class Scene extends GUI {
	val x = 0
	val y = 0
	
	def width = Graphics.width
	def height = Graphics.height
	
	def enter
	def leave
}