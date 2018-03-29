package aplicacion;

public class Tronco extends GameObject{
	
	public Tronco(int x, int y, int dx,int width, int height){
		super(x,y,dx,width,height);
	}
	
	public void mover()
	{	
		if(x>=1400) x = -185;
		x += dx;
	}
	
}
