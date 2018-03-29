package aplicacion;

import java.io.Serializable;
import java.util.ArrayList;
public class Carretera implements Serializable{
    public static final int PRIMERA = 613;
    public static final int SEGUNDA = 565;
    public static final int TERCERA = 517;
    public static final int CUARTA = 469;
    public static final int QUINTA = 415;
    //public static final int SEXTA = 367;
    //public static final int SEPTIMA = 319;
    public static final int OCTAVA = 265;
    public static final int NOVENA = 218;
    public static final int DECIMA = 168;
    public static final int ONCEAVA = 118;
    public static final int DOCEAVA = 68;
    public static final int TRECEAVA = 16;
    
    public ArrayList<Linea> carriles;
    public Carretera(){
    	carriles = new ArrayList<Linea>();
		llenarPrimerCarril();
        llenarSegundoCarril();
        llenarTercerCarril();
        llenarCuartoCarril();
        llenarQuintoCarril();
		llenarOctavoCarril();
		llenarNovenoCarril();
		llenarDecimoCarril();
		llenarOnceavoCarril();
		llenarDoceavoCarril();
		llenarTreceavoCarril();
    }
    
    private void llenarPrimerCarril(){
        Linea primerCarril = new Linea(5,280,2,PRIMERA,"Automovil1","izquierda");
        carriles.add(primerCarril);
    }
    
    private void llenarSegundoCarril(){
        Linea segundoCarril = new Linea(5,280,2,SEGUNDA,"Automovil2","derecha");
        carriles.add(segundoCarril);
    }
    
    private void llenarTercerCarril(){
        Linea tercerCarril = new Linea(5,280,2,TERCERA,"Automovil3","izquierda");
        carriles.add(tercerCarril);
    }
    
    private void llenarCuartoCarril(){
        Linea cuartoCarril = new Linea(2,100,3,CUARTA,"Automovil4","derecha");
        carriles.add(cuartoCarril);
    }
    
    private void llenarQuintoCarril(){
        Linea quintoCarril = new Linea(5,270,1,QUINTA,"Camion","izquierda");
        carriles.add(quintoCarril);
    }
    
    //private void llenarSextoCarril(){}
    //private void llenarSeptimoCarril(){}
    
    private void llenarOctavoCarril(){
    	Linea octavoCarril = new Linea(6,200,3,OCTAVA,"Tortuga3","izquierda");
    	carriles.add(octavoCarril);
    }
    
    private void llenarNovenoCarril(){
    	Linea novenoCarril = new Linea(8,200,1,NOVENA,"Tronco1","derecha");
    	carriles.add(novenoCarril);
    }
    
    private void llenarDecimoCarril(){
    	Linea decimoCarril = new Linea(5,315,3,DECIMA,"Tronco2","derecha");
    	carriles.add(decimoCarril);
    }
    
    
    private void llenarOnceavoCarril(){
    	Linea onceavoCarril = new Linea(7,200,2,ONCEAVA,"Tortuga2","izquierda");
    	carriles.add(onceavoCarril);
    }
    
    private void llenarDoceavoCarril(){
    	Linea doceavoCarril = new Linea(4,400,2,DOCEAVA,"Tronco3","derecha");
    	carriles.add(doceavoCarril);
    }
    
    private void llenarTreceavoCarril(){
    	Linea treceavoCarril = new Linea(TRECEAVA,"Llegada");
    	carriles.add(treceavoCarril);
    }
    
    
    public void mover(){
        for(Linea line: carriles){
            line.mover();
        }
    }
    
    public ArrayList<Linea> getCarriles()
    {
    	return carriles;
    }

}