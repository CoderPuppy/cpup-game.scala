package coderpuppy.lib.game

import coderpuppy.lib.game.events.EventHandler
import coderpuppy.lib.game.events.Event

object SceneManager extends EventHandler {
	protected var _currentScene: Scene = null
	def currentScene = _currentScene
	
	var fps = 60
	
	def run {
		val oldGraphicsHandler = Graphics.handler
		Graphics.handler = this
		val oldInputHandler = Input.handler
		Input.handler = this
		while(Graphics.running && _currentScene != null) {
			try {
				Graphics.update(() => {
					_currentScene.update
					_currentScene.render					
				})
				Input.update
				Graphics.sync(fps)
			} catch {
				case e: ExitException => {}
			}
		}
		Graphics.handler = oldGraphicsHandler
		Input.handler = oldInputHandler
	}
	
	def enter(scene: Scene) {
		if(_currentScene != null) {
			_currentScene.leave
		}
		_currentScene = scene
		_currentScene.enter
	}
	
	def exit {
		_currentScene = null
		throw new ExitException()
	}
	
	def onEvent(e: Event): Boolean = _currentScene.onEvent(e)
	
	class ExitException extends Exception {}
}