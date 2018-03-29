package presentacion;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class GameOver extends JPanel {
	private Image background;
	public static Clip sonido;
	public GameOver(){
		background = new ImageIcon(getClass().getResource("/Resources/roadkill.png")).getImage();
		try {
            sonido = AudioSystem.getClip();
            sonido.open(AudioSystem.getAudioInputStream(getClass().getResource("/Resources/GameOver.wav"))); 
            sonido.loop(0);
        } catch (Exception e) {
        	e.printStackTrace();
		}
	}
	public void paintComponent(Graphics g){
		g.drawImage(background,0,0,getWidth(),getHeight(),this);
	}
}