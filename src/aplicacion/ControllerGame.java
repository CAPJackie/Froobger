package aplicacion;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public class ControllerGame implements Serializable {
	private Random rand;
	private Player frog;
	private Carretera autopista;
	private boolean isDeath = false;
	private int realTime;
	private int initialTime;
	private boolean esBuena;
	private GameObject tresTortugas, dosTortugas;
	private int maximaAltura;
	private int contadorMuerte;
	private boolean isBonoLlegada;
	private int llegadaConBono;
	private ArrayList<GameObject> carril;
	public ControllerGame(){
		rand = new Random();
		llegadaConBono = 0;
		isBonoLlegada = false;
		contadorMuerte = 0;
		frog = new Player(79+(48*12), 657, 0,0);
		realTime = 60*50;
		esBuena = true;
		initialTime = 60;
		autopista = new Carretera();
		tresTortugas = autopista.getCarriles().get(5).getElementos().get(0);
		dosTortugas = autopista.getCarriles().get(8).getElementos().get(0);
		maximaAltura = frog.getY();
		carril = autopista.getCarriles().get(10).getElementos();
   }
   
   public void setDirection(int dx, int dy)
   {
	   //System.out.println(contadorMuerte);
       frog.setDirection(dx,dy);
   }
   
   
   public void tick() throws FroobgerException{
	   if(frog.getLives()==0 || realTime ==0) throw new FroobgerException(FroobgerException.GAME_OVER);
	   
	   if(maximaAltura>frog.getY()){
		   maximaAltura = frog.getY();
		   frog.setPuntaje(frog.getPuntaje()+5);
	   }
	   
	   if((realTime+2*50) % (3*50) == 0 && !isBonoLlegada){
		   
		   if(carril.get(llegadaConBono).getEstado()=="Vacio")carril.get(llegadaConBono).setEstado("Bono");
		   isBonoLlegada = true;
	   }
	   
	   else if(realTime % (3*50)==0 && isBonoLlegada){
		   if(carril.get(llegadaConBono).getEstado()=="Bono")carril.get(llegadaConBono).setEstado("Vacio");
		   llegadaConBono = rand.nextInt(5);
		   isBonoLlegada = false;
	   }
	   
	   if(realTime % (5*50) == 0 && !esBuena){
		   esBuena = true;
	   }
	   else if((realTime+2*50) % (5*50) == 0 && esBuena) {
		   esBuena = false;
	   }
	   if(esBuena){
		   
		   tresTortugas.setEstado("Buena");
		   dosTortugas.setEstado("Buena");
		   
	   }
	   else if(!esBuena){
		   tresTortugas.setEstado("Mala");
		   dosTortugas.setEstado("Mala");
	   }
	   realTime--;
	   if (isInLlegada()){
		   frog.setPuntaje(frog.getPuntaje()+500);
		   realTime+=10*50;
    	   reset();
       }
	   if(!isDeath)frog.mover();
       autopista.mover();
       String collision = collision();
       
       //System.out.println(contadorMuerte);
       if(collision =="Death" || collision == null && frog.getY()<=Carretera.OCTAVA)contadorMuerte++;
       if((collision == "Death" || collision == null && frog.getY()<=Carretera.OCTAVA) && frog.getEstado()=="Vivo"){
    	   isDeath = true;
    	   frog.muerte();
       }
       else if((collision==null || collision=="Carry") && frog.getEstado()=="Muerto" && contadorMuerte < (2 * 50)){
    	   contadorMuerte++;
       }
       if(contadorMuerte==45){
    	   //System.out.println("si");
    	   reset();
       }
       else  if(contadorMuerte==2*50){
    	   isDeath = false;
    	   frog.setEstado("Vivo");
    	   contadorMuerte=0;
       }
   }
   
   
   public void reset(){
	   frog.setX(79+(48*12));
	   frog.setY(657);
	   maximaAltura = frog.getY();
   }
   
   private String collision() throws FroobgerException{
	   boolean ans = false;
	   String collision = null;
	   ArrayList<Linea> carriles = autopista.getCarriles();
	   for(int i = 0; i < carriles.size() && !ans && collision!="Death"; i++){
		   ArrayList<GameObject> elementos = carriles.get(i).getElementos();
		   for(int j = 0; j < elementos.size() && !ans && collision!="Death"; j++){
			   ans = isIntersected(frog.getX(),frog.getY(),frog.getWidth(),frog.getHeight(),
					   elementos.get(j).getX(),elementos.get(j).getY(),elementos.get(j).getWidth(),elementos.get(j).getHeight());
			   if(elementos.get(j) instanceof Automovil/*AQUI VA EL OR PARA TODOS LOS ENEMIGOS*/ && ans)collision = "Death";
			   else if(((elementos.get(j) instanceof Tortuga && elementos.get(j).getEstado()=="Buena" )|| elementos.get(j) instanceof Tronco) && ans){
				   frog.setDx(elementos.get(j).getDx());
				   frog.mover();
				   collision = "Carry";
			   }
			   
		   }
	   }
	   return collision;
   }
   
   private boolean isInLlegada(){
	   boolean ans = false;
	   //ArrayList<GameObject> carril = autopista.getCarriles().get(10).getElementos();
	   for(int i = 0; i<carril.size() && !ans;i++){
		   GameObject llegada = carril.get(i);
		   if(llegada.getEstado()=="Vacio" || llegada.getEstado()=="Bono"){
			   ans = isIntersected(frog.getX(),frog.getY(),frog.getWidth(),frog.getHeight(),
					   llegada.getX()-5,
					   llegada.getY(),
					   llegada.getWidth(),
					   llegada.getHeight());
			   if(ans){
				   if(llegada.getEstado()=="Bono")frog.setPuntaje(frog.getPuntaje()+200);
				   llegada.setEstado("Lleno");
			   }
		   }
		   
		 
	   }
	   return ans;
   }
   
   
   
   private boolean isIntersected(int c1X, int c1Y, int c1W, int c1H, int c2X, int c2Y, int c2W, int c2H)
   {
	   return (c1X < c2X + c2W) 
		&& (c1X + c1W > c2X
		&& (c1Y <  c2Y + c2H)
		&& (c1Y + c1H > c2Y));
   }
   public Player getFrog(){
       return frog;
   }
   
   /*public boolean getCollisioned()
   {
	   return isDeath;
   }*/
   
   
   public ArrayList<Linea> getCarretera()
   {
      return autopista.getCarriles();
   }
   
   
   public int getRealTime(){
	   return realTime;
   }
   
   
   public void setRealTime(int realTime){
	   this.realTime = realTime;
   }
   
   public int getInitialTime(){
	   return initialTime;
   }
   
   public void setInitialTime(int initialTime){
	   this.initialTime = initialTime;
   }
   
   public boolean getIsDeath(){
	   return isDeath;
   }
   
   public int getContadorMuerte(){
	   return contadorMuerte;
   }
   
   public int getMaximaAltura(){
	   return maximaAltura;
   }
   
}
