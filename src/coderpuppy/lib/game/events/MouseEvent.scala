package coderpuppy.lib.game.events

trait MouseEvent extends InputEvent {
	def pos: (Int, Int)
}