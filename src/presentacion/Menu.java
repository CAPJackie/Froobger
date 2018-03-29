package presentacion;


import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import aplicacion.ControllerGame;


public class Menu extends JPanel {
	
	//IMAGEN DE FONDO
	private Image background;
	
	//BOTONES MENU
	private JButton play;
	private JButton load;
	private JButton instructions;
	private JButton credits;
	private JButton highScores;
	private JButton exit;
	private JLabel logo;
	public static Clip sonido;
	
	public Menu(){
		prepareElementos();
		prepareAcciones();
	}
	
	private void prepareElementos(){
		//IMAGEN FONDO
		background = new ImageIcon(getClass().getResource("/Resources/fondo.gif")).getImage();
		//QUITAR BORDERLAYOUT
		setLayout(null);
		//AGREGAR SONIDO
		
		try {
            sonido = AudioSystem.getClip();
            sonido.open(AudioSystem.getAudioInputStream(getClass().getResource("/Resources/intro.wav")));
            sonido.loop(Clip.LOOP_CONTINUOUSLY);   
            
        } catch (Exception e) {
		}
		//MENU
		prepareBotones();


	}
	
	private void prepareBotones() {
		ImageIcon logotipo = new ImageIcon(getClass().getResource("/Resources/logo.png"));
		ImageIcon fondo = new ImageIcon(getClass().getResource("/Resources/table.png"));
		fondo= new ImageIcon(fondo.getImage().getScaledInstance(534, 110, java.awt.Image.SCALE_DEFAULT));
		//INICIALIZAR BOTONES
		play= new JButton("Play",fondo);
		fondo= new ImageIcon(fondo.getImage().getScaledInstance(486, 100, java.awt.Image.SCALE_DEFAULT));
		load= new JButton("Load",fondo);
		instructions= new JButton("Instructions",fondo);
		credits= new JButton("Credits",fondo);
		highScores= new JButton("High-Scores",fondo);
		exit= new JButton("Exit :(",fondo) ;
		logo= new JLabel(logotipo);
		
		//POSICIONAR
		Dimension screenSize = FroobgerGUI.screenSize;
		play.setBounds(screenSize.width-600, screenSize.height-700,534,110);
		load.setBounds(screenSize.width-552, screenSize.height-560, 486, 110);
		instructions.setBounds(screenSize.width-552, screenSize.height-420, 486, 100);
		highScores.setBounds(screenSize.width-552, screenSize.height-290, 486, 100);
		exit.setBounds(screenSize.width-266, screenSize.height-160, 200, 70);
		credits.setBounds(screenSize.width-552, screenSize.height-160, 200, 70);
		logo.setBounds(screenSize.width-1350, screenSize.height-700, 580, 400);

		
		//TRANPARENCIA BOTONES
		play.setBorderPainted(false); 
		play.setContentAreaFilled(false); 
		play.setFocusPainted(false); 
		play.setOpaque(false);
		play.setHorizontalTextPosition(SwingConstants.CENTER);
		
		load.setBorderPainted(false); 
		load.setContentAreaFilled(false); 
		load.setFocusPainted(false); 
		load.setOpaque(false);
		load.setHorizontalTextPosition(SwingConstants.CENTER);
		
		instructions.setBorderPainted(false); 
		instructions.setContentAreaFilled(false); 
		instructions.setFocusPainted(false); 
		instructions.setOpaque(false);
		instructions.setHorizontalTextPosition(SwingConstants.CENTER);
		
		highScores.setBorderPainted(false); 
		highScores.setContentAreaFilled(false); 
		highScores.setFocusPainted(false); 
		highScores.setOpaque(false);
		highScores.setHorizontalTextPosition(SwingConstants.CENTER);
		
		
		//AJUSTAR TEXTO
		play.setHorizontalTextPosition(SwingConstants.CENTER);
		load.setHorizontalTextPosition(SwingConstants.CENTER);
		instructions.setHorizontalTextPosition(SwingConstants.CENTER);
		highScores.setHorizontalTextPosition(SwingConstants.CENTER);
		credits.setHorizontalTextPosition(SwingConstants.CENTER);
		exit.setHorizontalTextPosition(SwingConstants.CENTER);
		
		//DISENAR TEXTO
		Font fuente1 = new Font("Ravie", Font.BOLD, 72);
		Font fuente2 = new Font("Goudy Stout", Font.PLAIN, 30);
		Font fuente3 = new Font("Goudy Stout", Font.PLAIN, 18);
		play.setFont(fuente1);
		load.setFont(fuente2);
		instructions.setFont(fuente2);
		highScores.setFont(fuente2);
		credits.setFont(fuente3);
		exit.setFont(fuente3);
		play.setForeground(java.awt.Color.ORANGE);
		load.setForeground(java.awt.Color.yellow);
		instructions.setForeground(java.awt.Color.yellow);
		highScores.setForeground(java.awt.Color.yellow);
		credits.setForeground(java.awt.Color.yellow);
		exit.setForeground(java.awt.Color.yellow);
		
		//AGREGAR
		add(play);
		add(load);
		add(instructions);	
		add(credits);
		add(highScores);
		add(exit);
		add(logo);
	}

	private void prepareAcciones(){
		
		//EXIT
		exit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ev){
				accionSalir();
        }
		});
		
		//PLAY
		play.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ev){
				accionPlay();
			}
		});
		
		load.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ev){
				accionCargar();
			}
		});
		
		instructions.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ev){
				accionInstruccions();
			}
		});
		
		highScores.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ev){
				accionHighScores();
			}
		});
		
		credits.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ev){
				accionCredits();
			}
		});
		
	}
	
	private void accionSalir()
	{
		int confirmacion = JOptionPane.showConfirmDialog(null ,"Esta a punto de salir \nEsta seguro?","",JOptionPane.YES_NO_OPTION);
		if(confirmacion == JOptionPane.YES_OPTION)
		{
			setVisible(false);
			System.exit(0);
		}
	}    
	
	private void accionPlay(){
		
		Main.gui.getContentPane().removeAll();
		Main.gui.getContentPane().add(new playScreen(),BorderLayout.CENTER);
		Main.gui.validate();
		//sonido.stop();
	}
	
	
	private void accionCargar()	{
		File fichero=null;
		JFileChooser fc = new JFileChooser();
		int seleccion = fc.showOpenDialog(null);
		if(seleccion == JFileChooser.APPROVE_OPTION){
			fichero = fc.getSelectedFile();
		}
		ObjectInputStream in;
		try {
			in = new ObjectInputStream (new FileInputStream(fichero));
			Main.gui.getContentPane().removeAll();
			Main.gui.getContentPane().add(new GameCanvas((ControllerGame)in.readObject()), BorderLayout.CENTER);
			Main.gui.validate();	
			in.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		
	}
	
	private void accionInstruccions()
	{
		Main.gui.getContentPane().removeAll();
		Main.gui.getContentPane().add(new Instructions(), BorderLayout.CENTER);
		Main.gui.validate();
	}
	
	private void accionHighScores()
	{
		Main.gui.getContentPane().removeAll();
		Main.gui.getContentPane().add(new HighScores(), BorderLayout.CENTER);
		Main.gui.validate();
	}
	
	private void accionCredits()
	{
		Main.gui.getContentPane().removeAll();
		Main.gui.getContentPane().add(new Credits(), BorderLayout.CENTER);
		Main.gui.validate();
	}
            
	
	public void paintComponent(Graphics g){
		g.drawImage(background,0,0,getWidth(),getHeight(),this);
	}

}
