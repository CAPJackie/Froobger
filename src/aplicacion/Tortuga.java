package aplicacion;

public class Tortuga extends GameObject{

	public Tortuga(int x, int y, int dx,int width, int height,String estado){
		super(x,y,dx,width,height);
		this.estado = estado;
	}
	
	public void mover()
	{	
		x += dx;
		if(x<=-120) x = 1366;

	}
	
}