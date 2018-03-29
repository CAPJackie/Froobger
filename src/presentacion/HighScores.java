package presentacion;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class HighScores extends JPanel {
	private Dimension screenSize;
	private JButton botonAtras;
	private JPanel scores;
	
	private JLabel number;
	private JLabel name;
	private JLabel score;
	private JLabel imagenScores;
	private Image background;
	
	public HighScores(int score)
	{
		ImageIcon icono = new ImageIcon(getClass().getResource("/Resources/icono.png"));
		String nombre=null;
		while (nombre==null){
			nombre= JOptionPane.showInputDialog("Digita Tu Nombre:");
		}
		System.out.println(nombre);
		try {
			acomodeScores(nombre,score);
		} catch (IOException e) {
			e.printStackTrace();
		}
		prepareElementos();
		prepareAcciones();
	}
	
	public HighScores()
	{
		prepareElementos();
		prepareAcciones();
	}
	
	private void prepareElementos()
	{
		background = new ImageIcon(getClass().getResource("/Resources/woodenpanel.png")).getImage();
		ImageIcon cartel= new ImageIcon(getClass().getResource("/Resources/poster.png"));
		cartel= new ImageIcon(cartel.getImage().getScaledInstance(900,768, java.awt.Image.SCALE_DEFAULT));
		Font fuente = new Font("Tw Cen MT Condensed Extra Bold", Font.BOLD, 80);
		screenSize = FroobgerGUI.screenSize;
		setLayout(null);
		imagenScores = new JLabel(cartel);
		
		imagenScores.setBounds(233,0,900,768);
		imagenScores.setFont(fuente);
		
		
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
		
		try {
			prepareScores();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		add(botonAtras);
		add(imagenScores);
		
	}
	
	private void acomodeScores(String nombre, int score) throws IOException {
		URL url= getClass().getResource("/Resources/highScores.txt");
		InputStream is = url.openStream();
		InputStreamReader isr = new java.io.InputStreamReader(is, "UTF-8");
		BufferedReader hs= new BufferedReader(isr);	

		
		File f= new File(url.getFile());
		System.out.println(f.exists());
		RandomAccessFile archivo = new RandomAccessFile(f, "rw");
		
		String[] highScore={};
		String line= hs.readLine();
		Boolean bool= true;
		for(int i=0; i<=5 && bool;i++){
			if(line!=null){
				highScore=line.split(";");
				int num=Integer.parseInt (highScore[1]);
				if(num<score){
					archivo.seek(0);
					archivo.writeBytes(nombre+";"+score);
					bool=false;
				}
				else{
					line= hs.readLine();
				}
			}
		}
			hs.close();
			archivo.close();
		}
		

	
	
	private void prepareScores() throws IOException
	{
		URL url= getClass().getResource("/Resources/highScores.txt");
		InputStream is = url.openStream();
		InputStreamReader isr = new java.io.InputStreamReader(is, "UTF-8");
		BufferedReader hs= new BufferedReader(isr);	
		
		Font fuente = new Font("Playbill", Font.PLAIN, 70);
		Font fuente2 = new Font("Baskerville Old Face", Font.BOLD, 30);
		scores = new JPanel();
		scores.setLayout(new GridLayout(6,3));
		scores.setOpaque(false);
		
		number = new JLabel("TOP",JLabel.CENTER);
		name = new JLabel("NAME",JLabel.CENTER);
		score = new JLabel("SCORE",JLabel.CENTER);
		
		number.setFont(fuente);
		name.setFont(fuente);
		score.setFont(fuente);
		
		number.setForeground(Color.BLACK);
		name.setForeground(Color.BLACK);
		score.setForeground(Color.BLACK);
		
		score.setOpaque(false);
		number.setOpaque(false);
		name.setOpaque(false);
		
		
		scores.add(number);
		scores.add(name);
		scores.add(score);
				
		String[] highScore={};
		String line= hs.readLine();
		for(int i=0; i<15;i++)
		{
			
			JLabel dato = new JLabel();
			int columna= i%3;
			
			if(columna==0){
				dato.setText(""+((i/3)+1));
				highScore=line.split(";");
				if (line!=null) line= hs.readLine();
			}
			else if (columna==1){
				dato.setText(highScore[0]);
			}
			else{
				dato.setText(highScore[1]);
			}
			dato.setHorizontalAlignment(JLabel.CENTER);
			dato.setFont(fuente2);
			dato.setOpaque(false);
			scores.add(dato);
		}
		
		
		scores.setBounds((screenSize.width-800)/2,(screenSize.height)/4+40,800,400);
		add(scores);
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