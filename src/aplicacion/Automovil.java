package aplicacion;

import java.io.Serializable;

public class Automovil extends GameObject implements Serializable {
	private String orientacion;
	
	
	
	public Automovil(int x, int y, int dx, String orientacion,int width, int height){
		super(x,y,dx,width,height);
		this.orientacion = orientacion;
	}
	
	public void mover()
	{	
		x+=dx;
		if(orientacion == "derecha"){
			if(x>=1370) x = -70;
		}
		else{
			if(x<=-70) x = 1370;
		}
	}

	
}
