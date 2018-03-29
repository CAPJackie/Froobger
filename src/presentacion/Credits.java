package presentacion;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class Credits extends JPanel {
	private Image background;
	private JButton botonAtras;
	public Credits()
	{
		prepareElementos();
		prepareAcciones();
	}
	
	private void prepareElementos()
	{
		setLayout(null);
		background = new ImageIcon(getClass().getResource("/Resources/credits.png")).getImage();
		//BOTON ATRAS
		ImageIcon back = new ImageIcon(getClass().getResource("/Resources/backb.png"));
		back= new ImageIcon(back.getImage().getScaledInstance(150, 100, java.awt.Image.SCALE_DEFAULT));		
		botonAtras = new JButton("Back",back);
		botonAtras.setBounds(0,0,150,100);
		botonAtras.setBorderPainted(false); 
		botonAtras.setContentAreaFilled(false); 
		botonAtras.setFocusPainted(false); 
		botonAtras.setOpaque(false);
		add(botonAtras);
	}
	
	
	private void prepareAcciones()
	{
		botonAtras.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ev){
				accionAtras();
			}
		});
	}
	
	private void accionAtras(){
		Menu.sonido.stop();
		Main.gui.getContentPane().removeAll();
		Main.gui.getContentPane().add(new Menu(), BorderLayout.CENTER);
		Main.gui.validate();
	}
	
	public void paintComponent(Graphics g){
		g.drawImage(background,0,0,getWidth(),getHeight(),this);
	}
}
