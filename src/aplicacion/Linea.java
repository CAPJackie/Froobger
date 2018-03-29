package aplicacion;

import java.io.Serializable;
import java.util.ArrayList;

public class Linea implements Serializable {
	private int numeroDeObjetos;
	private int distanciaEntreObjetos;
	private int velocidadDeCadaObjeto;
	private int numeroDeLinea;
	private String tipo;
	private String direccion;
	
	private ArrayList<GameObject> elementos=new ArrayList<GameObject>();
	
	public Linea(int numeroDeLinea,String tipo){
		this.numeroDeLinea = numeroDeLinea;
		this.tipo = tipo;
		
		llenarLlegada();
	}
	
	public Linea(int numeroDeObjetos, int distanciaEntreObjetos, int velocidadDeCadaObjeto, int numeroDeLinea, String tipo, String direccion)
	{
		this.numeroDeObjetos = numeroDeObjetos;
		this.distanciaEntreObjetos = distanciaEntreObjetos;
		this.velocidadDeCadaObjeto = velocidadDeCadaObjeto;
		this.numeroDeLinea = numeroDeLinea;
		this.tipo = tipo;
		this.direccion = direccion;
		llenarLinea();
	}
	
	
	
	private void llenarLinea()
	{
		for(int i = 0; i<numeroDeObjetos;i++){
			GameObject objeto=null;
			if(tipo == "Automovil1")objeto = new Automovil(i*distanciaEntreObjetos,numeroDeLinea,-velocidadDeCadaObjeto,direccion,35,34);
			else if(tipo == "Automovil2")objeto = new Automovil(i*distanciaEntreObjetos,numeroDeLinea,+velocidadDeCadaObjeto,direccion,31,31);
			else if(tipo == "Automovil3")objeto = new Automovil(i*distanciaEntreObjetos,numeroDeLinea,-velocidadDeCadaObjeto,direccion,29,28);
			else if(tipo =="Automovil4")objeto = new Automovil(i*distanciaEntreObjetos,numeroDeLinea,+velocidadDeCadaObjeto,direccion,37,25);
			else if(tipo == "Camion")objeto = new Automovil(i*distanciaEntreObjetos,numeroDeLinea,-velocidadDeCadaObjeto,direccion,57,27);
			else if(tipo == "Tortuga3")objeto = new Tortuga(i*distanciaEntreObjetos,numeroDeLinea,-velocidadDeCadaObjeto,80,26,"Buena");
			else if(tipo == "Tortuga2") objeto = new Tortuga(i*distanciaEntreObjetos,numeroDeLinea,-velocidadDeCadaObjeto,35,26,"Buena");
			else if(tipo == "Tronco1") objeto = new Tronco(i*distanciaEntreObjetos,numeroDeLinea,velocidadDeCadaObjeto,35,25);
			else if(tipo == "Tronco2") objeto = new Tronco(i*distanciaEntreObjetos,numeroDeLinea,velocidadDeCadaObjeto,73,25);
			else if(tipo == "Tronco3") objeto = new Tronco(i*distanciaEntreObjetos,numeroDeLinea,velocidadDeCadaObjeto,135,25);
			elementos.add(objeto);
		}
	}
	
	private void llenarLlegada(){
		Llegada puesto1 = new Llegada(27,"Vacio");
		Llegada puesto2 = new Llegada(409,"Vacio");
		Llegada puesto3 = new Llegada(657,"Vacio");
		Llegada puesto4 = new Llegada(895,"Vacio");
		Llegada puesto5 = new Llegada(1286,"Vacio");
		
		elementos.add(puesto1);
		elementos.add(puesto2);
		elementos.add(puesto3);
		elementos.add(puesto4);
		elementos.add(puesto5);
	}
	
	
	public void mover()
	{
		for(int i = 0; i<numeroDeObjetos;i++){
			elementos.get(i).mover();
		}
	}
	
	public void setDistancia(int distancia)
	{
		distanciaEntreObjetos = distancia;
	}
	
	public void setVelocidad(int velocidad)
	{
		velocidadDeCadaObjeto = velocidad;
	}
	
	public void setNumeroObjetos(int numero)
	{
		numeroDeObjetos = numero;
	}
	
	public ArrayList<GameObject> getElementos()
	{
		return elementos;
	}
}

