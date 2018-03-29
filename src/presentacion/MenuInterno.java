package presentacion;

import java.awt.BorderLayout;
import javax.swing.JFrame;

import java.awt.Container;


public class MenuInterno extends JFrame {
	private Container contentPane;
	private GameCanvas juego;

	public MenuInterno(GameCanvas juego) {
		this.juego=juego;
		prepareElementos();
	}

	private void prepareElementos(){
		setUndecorated(true);
		contentPane = getContentPane();
		setBounds((int)Main.gui.getAlignmentX()+483, (int) Main.gui.getAlignmentY()+134, 400 , 500 );
		setResizable(false);
		contentPane.add(new MenuPausa(this), BorderLayout.CENTER);
		validate();
		
	}
	public void cerrarFrame(){
		setVisible(false);
		dispose();
		juego.resume();
		transferFocus();
	}	
	public GameCanvas getJuego(){
		return juego;
	}
	
}