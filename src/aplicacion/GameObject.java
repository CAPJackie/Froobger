package aplicacion;

import java.io.Serializable;

public abstract class GameObject implements Serializable {
	protected int x,y,dx,width,height;
	protected String estado;
	
	public GameObject(int x, int y, int dx,int width, int height){
		this.x = x;
		this.y = y;
		this.dx = dx;
		this.width = width;
		this.height = height;
	}
	public abstract void  mover();
	
	public int getX(){
		return x;
	}
	
	public void setX(int x){
		this.x = x;
	}
	
	public int getY(){
		return y;
	}
	
	public void setY(int y){
		this.y = y;
	}
	
	public int getDx(){
		return dx;
	}
	
	public void setDx(int dx){
		this.dx = dx;
	}
	
	public int getHeight(){
		return height;
	}
	
	public void setHeight(int height){
		this.height = height;
	}
	
	public int getWidth(){
		return width;
	}
	
	public void setWidth(int width){
		this.width = width;
	}
	
	public String getEstado()
	{
		return estado;
	}
	
	public void setEstado(String estado){
		this.estado = estado;
	}

}
