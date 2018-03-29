package presentacion;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class MenuPausa extends JPanel {
	private Image background;
	private JButton continuar;
	private JLabel logo;
	private JButton save;
	private JButton salir;
	private JButton customize;
	private JButton menu;
	private MenuInterno frame;
	
	public MenuPausa(MenuInterno frame) {
		this.frame=frame;
		prepareElementos();
		prepareAcciones();
	}
		
	public void prepareElementos(){
		Font fuente = new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 40);
		ImageIcon botones = new ImageIcon(getClass().getResource("/Resources/buttonmenu.png"));
		ImageIcon botones2= new ImageIcon(botones.getImage().getScaledInstance(295, 49, java.awt.Image.SCALE_DEFAULT));
		botones= new ImageIcon(botones.getImage().getScaledInstance(295, 89, java.awt.Image.SCALE_DEFAULT));
		background = new ImageIcon(getClass().getResource("/Resources/bgmenu.png")).getImage();
		setLayout(null);
		continuar = new JButton("Continue",botones);
		continuar.setBounds(50, 140, 300 , 90);
		continuar.setBorderPainted(false); 
		continuar.setContentAreaFilled(false); 
		continuar.setFocusPainted(false); 
		continuar.setOpaque(false);
		continuar.setHorizontalTextPosition(SwingConstants.CENTER);
		continuar.setFont(fuente);
		continuar.setForeground(java.awt.Color.WHITE);
		
		save = new JButton("Save",botones2);
		save.setBounds(50, 240, 300 , 50);
		save.setBorderPainted(false); 
		save.setContentAreaFilled(false); 
		save.setFocusPainted(false); 
		save.setOpaque(false);
		save.setHorizontalTextPosition(SwingConstants.CENTER);
		save.setFont(fuente);
		save.setForeground(java.awt.Color.WHITE);
		customize = new JButton("Select Frog",botones2);
		customize.setBounds(50, 300, 300 , 50);	
		customize.setBorderPainted(false); 
		customize.setContentAreaFilled(false); 
		customize.setFocusPainted(false); 
		customize.setOpaque(false);
		customize.setHorizontalTextPosition(SwingConstants.CENTER);
		customize.setFont(fuente);
		customize.setForeground(java.awt.Color.WHITE);
		menu = new JButton("Main Menu",botones2);
		menu.setBounds(50, 360, 300 , 50);	
		menu.setBorderPainted(false); 
		menu.setContentAreaFilled(false); 
		menu.setFocusPainted(false); 
		menu.setOpaque(false);
		menu.setHorizontalTextPosition(SwingConstants.CENTER);
		menu.setFont(fuente);
		menu.setForeground(java.awt.Color.WHITE);
		salir = new JButton("Exit",botones2);
		salir.setBounds(50, 420, 300 , 50);	
		salir.setBorderPainted(false); 
		salir.setContentAreaFilled(false); 
		salir.setFocusPainted(false); 
		salir.setOpaque(false);
		salir.setHorizontalTextPosition(SwingConstants.CENTER);
		salir.setFont(fuente);
		salir.setForeground(java.awt.Color.WHITE);
		add(continuar);
		add(customize);
		add(menu);
		add(save);
		add(salir);		
	}
	private void prepareAcciones() {
		continuar.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ev){
				accionCerrar();
			}
		});
		customize.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ev){
				accionRoom();
			}
		});
		menu.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ev){
				accionMenu();
			}
		});
		
		salir.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ev){
				accionSalir();
			}
		});
		save.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ev){
				accionGuardar();
			}
		});

	}

	
	private void accionRoom() {
		setVisible(false);
		frame.setVisible(false);
		Main.gui.getContentPane().removeAll();
		Main.gui.getContentPane().add(new playScreen(), BorderLayout.CENTER);
		Main.gui.validate();
	}
	private void accionMenu() {
		setVisible(false);
		frame.setVisible(false);
		Main.gui.getContentPane().removeAll();
		Main.gui.getContentPane().add(new Menu(), BorderLayout.CENTER);
		Main.gui.validate();
	}
	
	private void accionCerrar() {
		frame.cerrarFrame();
	}
	private void accionGuardar(){
		File f=null;
		JFileChooser fc = new JFileChooser(".");
		if ((fc.showOpenDialog(this)==JFileChooser.APPROVE_OPTION)) {
			f=fc.getSelectedFile();
		} else if ((fc.showSaveDialog(this)==JFileChooser.APPROVE_OPTION)) {
			f=fc.getSelectedFile();
		}
		ObjectOutputStream out;
		try {
			out = new ObjectOutputStream(new FileOutputStream(f));
			out.writeObject(frame.getJuego().getController());
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
			
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
	public void paintComponent(Graphics g){
		g.drawImage(background,0,0,getWidth(),getHeight(),null);
	}				
}
