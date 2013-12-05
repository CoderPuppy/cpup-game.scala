package coderpuppy.lib.game.events

trait EventHandler {
	def onEvent(e: Event): Boolean
}