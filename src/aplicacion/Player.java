package aplicacion;

import java.io.Serializable;

public class Player extends GameObject implements Serializable{
	private int dy;
	private String orientacion;
	private int lives;
	private int puntaje;
	public Player(int x, int y, int dx, int dy)
	{
		super(x,y,dx,40,31);
		puntaje = 0;
		lives = 5;
		orientacion = "arriba";
		this.dy =dy;
		this.estado ="Vivo";
	}
	
	public void setDirection(int dx, int dy)
	{
		this.dx = dx;
		this.dy = dy;
		if (dx==0 && dy>0){
			orientacion="abajo";
		}
		else if (dx==0 && dy<0){
			orientacion="arriba";
		}
		else if (dx>0 && dy==0){
			orientacion="derecha";
		}
		else if (dx<0 && dy==0){
			orientacion="izquierda";
		}
	}
	
	public void mover()
	{	
		//System.out.println(lives);
		if(dx!=0)x += dx;
		else if(dy!=0)y += dy;
		
		if(x<=79-(2*48))x = 79-(2*48) ;
		else if(x>=1327) x = 1327;
		else if(y<=40) y = 40;
		else if(y>=657) y = 657; 
	}
	
	public void muerte()
	{
		estado = "Muerto";
		lives--;
	}
	
	
	public int getDy(){
		return dy;
	}
	
	public void setDy(int dy)
	{
		this.dy = dy;
	}
	
	public String getOrientacion()
	{
		return orientacion;
	}
	
	public void setOrientacion(String orientacion)
	{
		this.orientacion = orientacion;
	}
	
	public int getLives()
	{
		return lives;
	}
	
	public void setLives(int lives)
	{
		this.lives = lives;
	}
	
	public int getPuntaje(){
		return puntaje;
	}
	
	public void setPuntaje(int puntaje){
		this.puntaje = puntaje;
	}
}
