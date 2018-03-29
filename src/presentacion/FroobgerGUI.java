package presentacion;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;



public class FroobgerGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	public static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	//public static Container contentPane;
	private Container contentPane;
	public FroobgerGUI(){
		prepareElementos();
		prepareAcciones();
	}
	
	private void prepareElementos(){
		contentPane = getContentPane();
		setTitle("Froobger");
		//setExtendedState(JFrame.MAXIMIZED_BOTH);
		setSize(1366,768);
		setResizable(false);
	
		setIconImage(new ImageIcon(getClass().getResource("/Resources/icono.png")).getImage());
		contentPane.add(new Menu(), BorderLayout.CENTER);
		validate();
	}
	private void prepareAcciones(){
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent ev){
				accionSalir();
			}
		});
	}
	
	private void accionSalir()
	{
		int confirmacion = JOptionPane.showConfirmDialog( null ,"Esta a punto de salir \nEsta seguro?","",JOptionPane.YES_NO_OPTION);
		if(confirmacion == JOptionPane.YES_OPTION)setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		else setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	}
}
