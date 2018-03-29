package presentacion;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class ResourceLoader {
	
	private BufferedImage image;
	
	public BufferedImage loadImage(String path)
	{
		try {
			image = ImageIO.read(getClass().getResource(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return image;
	}
	
	public void loadMusic(String path){
		try {
            Clip sonido = AudioSystem.getClip();
     
            sonido.open(AudioSystem.getAudioInputStream(getClass().getResource(path)));
            sonido.start();           
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

}
