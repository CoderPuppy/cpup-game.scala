package coderpuppy.lib.game

import org.lwjgl.opengl.GL11._
import java.awt.image.BufferedImage
import org.newdawn.slick.opengl.ImageIOImageData
import org.newdawn.slick.opengl.InternalTextureLoader
import org.lwjgl.opengl.GLContext
import org.lwjgl.opengl.EXTTextureMirrorClamp

class BufferedImageTexture(val img: BufferedImage) extends Texture {
	val txID = InternalTextureLoader.createTextureID
	
	update
	
	def width = img.getWidth
	def height = img.getHeight
	
	def bind {
		glBindTexture(GL_TEXTURE_2D, txID)
	}
	
	def update {
		val txWidth = img.getWidth
		val txHeight = img.getHeight
		
		var data = new ImageIOImageData
		var srcPixelFormat = 0;	
	
		// Enable texturing
		glEnable(GL_TEXTURE_2D)
	
		// bind this texture
		glBindTexture(GL_TEXTURE_2D, txID)
	
		var bufferedImage = img;
	
		if (bufferedImage.getColorModel().hasAlpha()) {
			srcPixelFormat = GL_RGBA;
		} else {
			srcPixelFormat = GL_RGB;
		}
	
		// convert that image into a byte buffer of texture data
		var textureBuffer = data.imageToByteBuffer(bufferedImage, false, false, null)
		
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
		
        if(GLContext.getCapabilities().GL_EXT_texture_mirror_clamp) {
        	glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, EXTTextureMirrorClamp.GL_MIRROR_CLAMP_TO_EDGE_EXT)
        	glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, EXTTextureMirrorClamp.GL_MIRROR_CLAMP_TO_EDGE_EXT)
        } else {
        	glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_CLAMP)
        	glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP)
        }
	
		glTexImage2D(GL_TEXTURE_2D,
			0,
			GL_RGBA8,
			txWidth,
			txHeight,
			0,
			srcPixelFormat,
			GL_UNSIGNED_BYTE,
			textureBuffer)
	}
}