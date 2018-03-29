package presentacion;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractButton;
import javax.swing.ImageIcon;

public class playScreen extends JPanel {

	
	private JButton normal;
	private JLabel  encabezado;
	private JButton botonAtras;
	private ResourceLoader songLoader;
    private Image background;
	private AbstractButton doom;
	private ImageIcon doomIcon;
	private ImageIcon psicoIcon;
	private JButton psico;
	private ImageIcon desiertoIcon;
	private JButton desierto;

	public playScreen()
	{
		prepareElementos();
		prepareAcciones();
	}
	
	private void prepareElementos()
	{
		//SONIDO
		songLoader = new ResourceLoader();
		
		//IMAGEN FONDO
		background = new ImageIcon(getClass().getResource("/Resources/backmenu.jpg")).getImage();
		setLayout(null);
		prepareBotones();
			
		//ENCABEZADO
		ImageIcon cabeza = new ImageIcon(getClass().getResource("/Resources/mid.png"));
		cabeza= new ImageIcon(cabeza.getImage().getScaledInstance(550, 150, java.awt.Image.SCALE_DEFAULT));
		encabezado = new JLabel(cabeza);
		encabezado.setBounds(408,0,550,150);
		add(encabezado);

	}
	
	
	private void prepareBotones() {
		int ancho=350;
		int alto=260;
		//BOTON NORMAL
		ImageIcon normalIcon = new ImageIcon(getClass().getResource("/Resources/normal.png"));
		normalIcon= new ImageIcon(normalIcon.getImage().getScaledInstance(ancho,alto, java.awt.Image.SCALE_DEFAULT));		
		normal = new JButton(normalIcon);
		normal.setBorderPainted(false); 
		normal.setContentAreaFilled(false); 
		normal.setFocusPainted(false); 
		normal.setOpaque(false);
		normal.setBounds(190,180,ancho,alto);
		
		//BOTON DOOM
		doomIcon = new ImageIcon(getClass().getResource("/Resources/doom.png"));
		doomIcon= new ImageIcon(doomIcon.getImage().getScaledInstance(ancho,alto, java.awt.Image.SCALE_DEFAULT));		
		doom = new JButton(doomIcon);
		doom.setBorderPainted(false); 
		doom.setContentAreaFilled(false); 
		doom.setFocusPainted(false); 
		doom.setOpaque(false);
		doom.setBounds(830,180,ancho,alto);		
		
		//BOTON PSICO
		psicoIcon = new ImageIcon(getClass().getResource("/Resources/psico.png"));
		psicoIcon= new ImageIcon(psicoIcon.getImage().getScaledInstance(ancho,alto, java.awt.Image.SCALE_DEFAULT));		
		psico = new JButton(psicoIcon);
		psico.setBorderPainted(false); 
		psico.setContentAreaFilled(false); 
		psico.setFocusPainted(false); 
		psico.setOpaque(false);
		psico.setBounds(830,450,ancho,alto);	
		
		//BOTON DESIERTO
		desiertoIcon = new ImageIcon(getClass().getResource("/Resources/desierto.png"));
		desiertoIcon= new ImageIcon(desiertoIcon.getImage().getScaledInstance(ancho,alto, java.awt.Image.SCALE_DEFAULT));		
		desierto = new JButton(desiertoIcon);
		desierto.setBorderPainted(false); 
		desierto.setContentAreaFilled(false); 
		desierto.setFocusPainted(false); 
		desierto.setOpaque(false);
		desierto.setBounds(190,450,ancho,alto);	
		
		//BOTON ATRAS
		ImageIcon back = new ImageIcon(getClass().getResource("/Resources/indice.png"));
		back= new ImageIcon(back.getImage().getScaledInstance(200, 150, java.awt.Image.SCALE_DEFAULT));		
		botonAtras = new JButton("Back",back);
		botonAtras.setBounds(0,0,200,150);
		botonAtras.setBorderPainted(false); 
		botonAtras.setContentAreaFilled(false); 
		botonAtras.setFocusPainted(false); 
		botonAtras.setOpaque(false);
		
		
		
		
		add(normal);
		add(doom);
		add(psico);
		add(desierto);
		add(botonAtras);
	}

	private void prepareAcciones()
	{
		botonAtras.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ev){
				accionAtras();
			}
		});
		
		normal.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ev){
				accionJugar("normal");
			}
		});
		doom.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ev){
				accionJugar("doom");
			}
		});
		desierto.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ev){
				accionJugar("desert");
			}
		});
		psico.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ev){
				accionJugar("psico");
			}
		});
	}
	
	private void accionAtras(){
		Menu.sonido.stop();
		Main.gui.getContentPane().removeAll();
		Main.gui.getContentPane().add(new Menu(), BorderLayout.CENTER);
		Main.gui.validate();
	}
	
	private void accionJugar(String skin)
	{
		if(skin=="normal") songLoader.loadMusic("/Resources/dp_frogger_coin.wav");
		else if (skin=="doom") songLoader.loadMusic("/Resources/doom.wav");
		else if (skin=="desert") songLoader.loadMusic("/Resources/egypt.wav");
		else songLoader.loadMusic("/Resources/psico.wav");
		Main.gui.getContentPane().removeAll();
		Menu.sonido.stop();
		Main.gui.getContentPane().add(new GameCanvas(skin), BorderLayout.CENTER);
		Main.gui.repaint();
		Main.gui.validate();
		Main.gui.setSize(1366,768);
		Main.gui.setResizable(false);
		songLoader.loadMusic("/Resources/intro - song.wma");
		
	}
	
		
	public void paintComponent(Graphics g){
		g.drawImage(background,0,0,getWidth(),getHeight(),this);
	}
}
