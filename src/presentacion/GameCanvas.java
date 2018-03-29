package presentacion;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.util.ArrayList;

import aplicacion.Automovil;


import aplicacion.ControllerGame;
import aplicacion.FroobgerException;
import aplicacion.GameObject;
import aplicacion.Linea;
import aplicacion.Llegada;
import aplicacion.Player;

public class GameCanvas extends Canvas implements Runnable, Serializable {
	
	private static final long serialVersionUID = 1L;
	private boolean running = false;
    private Thread thread;
    private String skin;
    private ControllerGame controller;
    private ArrayList<Linea> carretera;
    
    private int tiempoInicial;
    
    private BufferedImage background;
    
    private BufferedImage[] imagenesCarro;
    private BufferedImage[] imagenesTortugaTres;
    private BufferedImage[] imagenesTortugaTresMala;
    private BufferedImage[] imagenesTortugaDosMala;
    
    private Player rana;
    private BufferedImage mosca;
	
    public static BufferedImage frog_arriba_1;
	public static BufferedImage frog_arriba_2;
	
	public static BufferedImage frog_derecha_1;
	public static BufferedImage frog_derecha_2;
	
	public static BufferedImage frog_abajo_1;
	public static BufferedImage frog_abajo_2;
	
	public static BufferedImage frog_izquierda_1;
	public static BufferedImage frog_izquierda_2;
	
	public static BufferedImage[] timeLetters;
	
	public static BufferedImage muerte_1;
	public static BufferedImage muerte_2;
	public static BufferedImage muerte_3;
	public static BufferedImage frogImage;
	public static BufferedImage carImage;
	public int puntaje;
	
	public boolean muerteSonido=false;
	
	private BufferedImage frogSelectedSprite;
	
	private BufferedImage tractomula;
	private BufferedImage retroescavadora;
	private BufferedImage convertible;
	private BufferedImage deportivo;
	private BufferedImage camion_Monstruo;
	private BufferedImage[] numerosArray;
	private BufferedImage imagenLetras;
	
	public String score;
	
	private BufferedImage[] imagenesMadera;
	
	private ResourceLoader loader;
	private boolean is_moving = false;
	
	private int contDeath;
	private BufferedImage[] imagenesMuerte;
	private BufferedImage[] frogRight;
	private boolean timeOutSound = false;
	
	private BufferedImage ranaLlegada;
	
	
	private int actualSprite;
	private int actualSpriteMala;
	private BufferedImage llegada;
	private MenuInterno menuPausa;
	private boolean paused;
	private BufferedImage[] imagenesTortugaDos;
	private BufferedImage imagenVidas;
	private BufferedImage imagenScore;
	

    	
    public GameCanvas(String skin){
    	this.skin=skin;
    	menuPausa=new MenuInterno(this);
    	start();
    }
    
    public GameCanvas(ControllerGame controller){
    	skin="normal";
    	this.controller=controller;
    	menuPausa=new MenuInterno(this);
    	start();
    }
    
	public void init(){
		requestFocus();
		if (controller==null)controller = new ControllerGame();
		contDeath = controller.getContadorMuerte();
        loader = new ResourceLoader();
        background= loader.loadImage("/Resources/fondo.jpeg");
        loadFrog();
        loadCars();
        loadTurtles();
        loadWoods();
        loadBonos();
        loadVidas();
        addKeyListener(new InputHandler(this));
        carretera = controller.getCarretera();
        rana = controller.getFrog();
        tiempoInicial = controller.getInitialTime();
        loadScore();
        loadTime();
        
	}
	
	private void loadFrog()
	{
		BufferedImage frogSprites;
		if(skin=="normal") frogSprites = loader.loadImage("/Resources/frog_sprites.png");
		else if (skin=="doom") frogSprites = loader.loadImage("/Resources/rana roja sprite.png");
		else if (skin=="desert") frogSprites = loader.loadImage("/Resources/rana naranja sprite.png");
		else frogSprites = loader.loadImage("/Resources/rana azul sprite.png");
		SpriteSheet ss = new SpriteSheet(frogSprites);
		frog_arriba_1 = ss.grabImage(0, 0, 40, 40);
        frog_arriba_2 = ss.grabImage(40, 0, 40, 40);
		
		frog_derecha_1 = ss.grabImage(80, 0, 40, 40);
		frog_derecha_2 = ss.grabImage(120, 0, 40, 40);
		
		frogRight = new BufferedImage[2];
		frogRight[0] = frog_derecha_2;
		frogRight[1] = frog_derecha_1; 
		
		frog_abajo_1 = ss.grabImage(160, 0, 40, 40);
		frog_abajo_2 = ss.grabImage(200, 0, 40, 40);
		
		frog_izquierda_1 = ss.grabImage(240, 0, 40, 40);
		frog_izquierda_2 = ss.grabImage(280, 0, 40, 40);
		
		
		
		
		muerte_1 = ss.grabImage(320, 0, 40, 40);
		muerte_2 = ss.grabImage(360, 0, 40, 40);
		muerte_3 = ss.grabImage(400, 0, 40, 40);
		
		imagenesMuerte = new BufferedImage[3];
		
		imagenesMuerte[0] = muerte_1;
		imagenesMuerte[1] = muerte_2;
		imagenesMuerte[2] = muerte_3;
		
		
		
		frogSelectedSprite = frog_arriba_1;
	}
	private void loadCars(){
		BufferedImage carSprites = loader.loadImage("/Resources/car_sprites.png");
		
		SpriteSheet ss = new SpriteSheet(carSprites);
		tractomula = ss.grabImage(0, 0, 40, 40);
		retroescavadora = ss.grabImage(40, 0, 40, 40);
		deportivo = ss.grabImage(80, 0, 40, 40);
		convertible = ss.grabImage(120, 0, 40, 40);
		camion_Monstruo = loader.loadImage("/Resources/truck.png");
		
		imagenesCarro = new BufferedImage[5];
		imagenesCarro[0] = tractomula;
		imagenesCarro[1] = retroescavadora;
		imagenesCarro[2] = convertible;
		imagenesCarro[3] = deportivo;
		imagenesCarro[4] = camion_Monstruo;
	}
	private void renderCars(Graphics g)
	{
		BufferedImage selectedCar;
		//g.setColor(Color.YELLOW);
		for(int i = 0; i < 5; i++){
			selectedCar = imagenesCarro[i];
			ArrayList<GameObject> actual = carretera.get(i).getElementos();
			for(int j = 0; j < actual.size();j++){
				g.drawImage(selectedCar, actual.get(j).getX(), actual.get(j).getY(), this);
				//g.fillRect(actual.get(j).getX(), actual.get(j).getY(), actual.get(j).getWidth(),actual.get(j).getHeight() );
			}
		}
		
	}
	
	
	private void renderTurtles(Graphics g){
		int i=5;
			while(i<=8){
				ArrayList<GameObject> actual = carretera.get(i).getElementos();
				//g.setColor(Color.ORANGE);
				for(int j = 0; j < actual.size(); j++){
					//System.out.println(actualSprite);
					if(actual.get(j).getEstado()=="Buena"){
							if (i==5 && actualSprite<4){
								
									g.drawImage(imagenesTortugaTres[actualSprite],
										actual.get(j).getX()-25, actual.get(j).getY(), this);
								}
								
								//g.fillRect(actual.get(j).getX(), actual.get(j).getY(),actual.get(j).getWidth() , actual.get(j).getHeight());
							
							else if(i==5 && actualSprite==4){
								g.drawImage(imagenesTortugaTres[0],
										actual.get(j).getX()-25, actual.get(j).getY(), this);
								
							}
							else if(i==8 && actualSprite<4) {
								g.drawImage(imagenesTortugaDos[actualSprite],
									actual.get(j).getX()-25, actual.get(j).getY(), this);
								//g.fillRect(actual.get(j).getX(), actual.get(j).getY(),actual.get(j).getWidth() , actual.get(j).getHeight());
							}
							else if(i==8 && actualSprite==4){
								g.drawImage(imagenesTortugaDos[0],
										actual.get(j).getX()-25, actual.get(j).getY(), this);
							}
					}
					else if(actual.get(j).getEstado()=="Mala"){
							if(i==5 && actualSpriteMala<5){
								g.drawImage(imagenesTortugaTresMala[actualSpriteMala], 
										actual.get(j).getX()-25, actual.get(j).getY(), this);
							}
							else if(i==5 && actualSpriteMala==5){
								g.drawImage(imagenesTortugaTresMala[0], 
										actual.get(j).getX()-25, actual.get(j).getY(), this);
							}
							else if(i==8 && actualSpriteMala<5){
								g.drawImage(imagenesTortugaDosMala[actualSpriteMala], 
										actual.get(j).getX()-25, actual.get(j).getY(), this);
							}
							else if(i==8 && actualSpriteMala==5){
								g.drawImage(imagenesTortugaDosMala[0], 
										actual.get(j).getX()-25, actual.get(j).getY(), this);
							}
					}
				}
				i+=3;
			}
			i=5;
		
		
	}
	
	private void renderWoods(Graphics g){
		BufferedImage selectedWood;
		for(int i = 0; i < 3; i++){
			selectedWood = imagenesMadera[i];
			ArrayList<GameObject> actual;
			if(i<2)actual = carretera.get(i+6).getElementos();
			else actual = carretera.get(9).getElementos();
			for(int j = 0; j < actual.size(); j++){
				//g.setColor(Color.RED);
				g.drawImage(selectedWood, actual.get(j).getX()-30, actual.get(j).getY(), this);
				//g.fillRect(actual.get(j).getX(), actual.get(j).getY(), actual.get(j).getWidth(), actual.get(j).getHeight());
			}
		}
	}
	
	private void loadBonos(){
		ranaLlegada = loader.loadImage("/Resources/bonus.png");
		SpriteSheet ss = new SpriteSheet(ranaLlegada);
		
		llegada = ss.grabImage(83, 0, 37, 40);
		mosca = ss.grabImage(7, 9, 30, 25);
		//FALTA EL DINOSAURIO
	}
	private void loadVidas(){
		imagenVidas = loader.loadImage("/Resources/lives.png");
	}
	private void renderVidas(Graphics g){
		
		for(int i = 0;i<rana.getLives()-1;i++){
			g.drawImage(imagenVidas, (i*18)+18, 710, this);
		}
	}
	
	private void renderLlegada(Graphics g){
		ArrayList<GameObject> actual = carretera.get(10).getElementos();
		for(int i = 0; i< actual.size();i++){
			/*g.setColor(Color.GREEN);
			g.fillRect(actual.get(i).getX(), actual.get(i).getY(), actual.get(i).getWidth(), actual.get(i).getHeight());*/
			if(actual.get(i).getEstado()=="Lleno"){
				g.drawImage(llegada, actual.get(i).getX(), actual.get(i).getY(),this);
			}
			else if(actual.get(i).getEstado()=="Bono"){
				g.drawImage(mosca, actual.get(i).getX()+5, actual.get(i).getY()+5,this);
			}
		}
	}
	
	
	private void loadWoods(){
		imagenesMadera = new BufferedImage[3];
		imagenesMadera[0] = loader.loadImage("/Resources/pequeno.png");
		imagenesMadera[1] = loader.loadImage("/Resources/medi.png");
		imagenesMadera[2] = loader.loadImage("/Resources/grande.png");
	}
	
	
	private void loadTurtles(){
		BufferedImage turtleSprites = loader.loadImage("/Resources/Turtles.png");
		
		
		SpriteSheet ss = new SpriteSheet(turtleSprites);
		
		BufferedImage imagen1 = ss.grabImage(0, 0, 139, 40);
		BufferedImage imagen2 = ss.grabImage(139,0,141,40);
		BufferedImage imagen3 = ss.grabImage(279, 0, 141, 40);
		imagenesTortugaTres = new BufferedImage[4];
		imagenesTortugaTres[0] = imagen1;
		imagenesTortugaTres[1] = imagen2;
		imagenesTortugaTres[2] = imagen3;
		imagenesTortugaTres[3] = imagen2;
		BufferedImage imagen1MalaTres = ss.grabImage(417, 0, 120, 40);
		BufferedImage imagen2MalaTres = ss.grabImage(542, 0, 150, 40);
		BufferedImage invisible = ss.grabImage(729, 0, 2, 2);
		imagenesTortugaTresMala = new BufferedImage[5];
		imagenesTortugaTresMala[0] = imagen1MalaTres;
		imagenesTortugaTresMala[1] = imagen2MalaTres;
		imagenesTortugaTresMala[2] = invisible;
		imagenesTortugaTresMala[3] = imagen2MalaTres;
		imagenesTortugaTresMala[4] = imagen1MalaTres;
				
		
		
		
		
		BufferedImage imagenUno = ss.grabImage(0, 0, 93, 40);
		BufferedImage imagenDos = ss.grabImage(140, 0, 88, 40);
		BufferedImage imagenTres = ss.grabImage(279, 0, 87, 40);
		imagenesTortugaDos = new BufferedImage[4];
		imagenesTortugaDos[0] = imagenUno;
		imagenesTortugaDos[1] = imagenDos;
		imagenesTortugaDos[2] = imagenTres;
		imagenesTortugaDos[3] = imagenDos;
		BufferedImage imagen1MalaDos = ss.grabImage(417, 0, 80, 40);
		BufferedImage imagen2MalaDos = ss.grabImage(542, 0, 100, 40);
		imagenesTortugaDosMala = new BufferedImage[5];
		imagenesTortugaDosMala[0] = imagen1MalaDos;
		imagenesTortugaDosMala[1] = imagen2MalaDos;
		imagenesTortugaDosMala[2] = invisible;
		imagenesTortugaDosMala[3] = imagen2MalaDos;
		imagenesTortugaDosMala[4] = imagen1MalaDos;
		
		actualSprite = 0;
		actualSpriteMala = 0;
		
		
		
	}
        
	
	public void setDirection(int dx, int dy){
        controller.setDirection(dx, dy);
    }
	
	
    
    public void keyPressed(KeyEvent e){
    	
    	int key = e.getKeyCode();
    	boolean booleanMuerte = controller.getIsDeath();
    	if(!is_moving){
    		if(!booleanMuerte){
				if(key==KeyEvent.VK_RIGHT){	
					loader.loadMusic("/Resources/dp_frogger_hop.wav");
			        setDirection(24,0);
			        if(frogSelectedSprite != frog_derecha_2)frogSelectedSprite = frog_derecha_2;
		        }
				else if(key==KeyEvent.VK_LEFT){
					loader.loadMusic("/Resources/dp_frogger_hop.wav");
		        	setDirection(-24,0);
		        	if(frogSelectedSprite != frog_izquierda_2)frogSelectedSprite = frog_izquierda_2;
		        }
				else if(key==KeyEvent.VK_UP){
					loader.loadMusic("/Resources/dp_frogger_hop.wav");
		        	setDirection(0,-25);
		        	if(frogSelectedSprite != frog_arriba_2)frogSelectedSprite = frog_arriba_2;
		        }
				else if(key==KeyEvent.VK_DOWN){	
					loader.loadMusic("/Resources/dp_frogger_hop.wav");
		        	setDirection(0,25);
		        	if(frogSelectedSprite != frog_abajo_2)frogSelectedSprite = frog_abajo_2;
		        }
				else if(key==KeyEvent.VK_ESCAPE){
					loader.loadMusic("/Resources/back.wav");
					if (paused) resume();
					else pause();
				}
    		}
    	}
    	is_moving = true;
        
    }
    
    public void keyReleased(KeyEvent e) {

    	boolean booleanMuerte = controller.getIsDeath();
	    	if(!booleanMuerte){
		    	int key = e.getKeyCode();
		    	if(key == KeyEvent.VK_RIGHT){
		    		setDirection(24,0);
		    		frogSelectedSprite = frog_derecha_1;
		    	}
		    	else if(key == KeyEvent.VK_LEFT){
		    		setDirection(-24,0);
		    		frogSelectedSprite = frog_izquierda_1;
		    	}
		    	else if(key == KeyEvent.VK_UP){
		    		setDirection(0,-24);
		    		frogSelectedSprite = frog_arriba_1;
		    	}
		    	else if(key == KeyEvent.VK_DOWN){
		    		setDirection(0,24);
		    		frogSelectedSprite = frog_abajo_1;
		    	}
    	}
    	is_moving = false;
		
    }
    

    
	
	private synchronized void start(){
        if(running) return;

        running = true;
        thread = new Thread(this);
        thread.start();
    }
	private void pause(){
		paused=true;
		menuPausa.setVisible(true);
	}
	
	public void resume(){
		paused=false;
	}	
    private synchronized void stop(){
        if(!running) return;

        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.exit(1);
    }
	
    
    public void run() {
        init();
        long lastTime = System.nanoTime();
        final double numOfTicks = 60.0;
        double ns = 1000000000 / numOfTicks;
        double delta = 0;
        //int updates = 0;
        //int frames = 0;
        long timer = System.currentTimeMillis();

        while(running){
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            if (paused) delta=0;
            if(delta >= 1){
                tick();
                //updates++;
                delta--;
            }
            render();
            //frames++;

            if(System.currentTimeMillis() - timer > 1000){
                timer += 1000;
                //System.out.println(updates + "ticks, fps " + frames);
                //updates = 0;
                //frames = 0;
            }
        }
        stop();
    }
	
    /*
     * Run the ticks of all game components.
     */
    public void tick(){
    	//System.out.println(contDeath);
		try {
			controller.tick();
			
		} catch (FroobgerException e) {
			if(e.getMessage()==FroobgerException.GAME_OVER)running = false;
			finalJuego();
		}
		setDirection(0,0);
		animationTurtle();
		if(controller.getContadorMuerte()>0 && controller.getContadorMuerte()<=46)death();
		if(controller.getIsDeath()&&!muerteSonido){
			loader.loadMusic("/Resources/sound-frogger-squash.wav");
			muerteSonido=true;
		}
		else if(!controller.getIsDeath() && muerteSonido)muerteSonido = false;
        
    }
    
    private void finalJuego() {
    	try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    	Main.gui.getContentPane().removeAll();
    	Main.gui.getContentPane().add(new GameOver(), BorderLayout.CENTER);
    	Main.gui.validate();  
    	try {
			Thread.sleep(2500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Main.gui.getContentPane().removeAll();
		Main.gui.getContentPane().add(new HighScores(rana.getPuntaje()), BorderLayout.CENTER);
		Main.gui.validate();    		
	}

	private void animationTurtle(){
    	
    	if(actualSprite<4 && controller.getRealTime()%25==0)actualSprite++;
    	else if(actualSprite==4 && controller.getRealTime()%25==0) actualSprite=0;
    	
    	
    	if(actualSpriteMala<5 && controller.getRealTime()%30==0) actualSpriteMala++;
    	else if(actualSpriteMala==5 && controller.getRealTime()%30==0) actualSpriteMala = 0;
    }

    
    private void death()
    {
    	int contador = controller.getContadorMuerte();
    	//System.out.println(contador);
    	if(contador>0 && contador<16)frogSelectedSprite = imagenesMuerte[0];
    	else if(contador<31)frogSelectedSprite = imagenesMuerte[1];
    	else if(contador<46)frogSelectedSprite = imagenesMuerte[2];
    	else if(contador==46)frogSelectedSprite = frog_arriba_1;
    }
    
    private void loadTime(){
    	timeLetters = new BufferedImage[4];
    	
    	imagenLetras = loader.loadImage("/Resources/letters.png");
    	SpriteSheet ss = new SpriteSheet(imagenLetras);
    	
    	timeLetters[0] = ss.grabImage(338, 80, 20, 20);
    	timeLetters[1] = ss.grabImage(302, 43, 20, 20);
    	timeLetters[2] = ss.grabImage(77, 80, 20, 20);
    	timeLetters[3] = ss.grabImage(153, 43, 20, 20);
    	
    }
    
    private void renderTime(Graphics g){
    	for(int i = 0; i< timeLetters.length;i++){
    		g.drawImage(timeLetters[i], i*20+1260,718,this);
    	}
    	int tiempoActual = controller.getRealTime();
    	//System.out.println(tiempo/50);
    	if (tiempoActual<(tiempoInicial/6)*50){
    		g.setColor(Color.RED);
    		if(!timeOutSound){
    			loader.loadMusic("/Resources/runs-out-of-time.wav");
    			timeOutSound = true;
    		}
    	}
    	for(int i=0; i<=(tiempoActual/50);i++){
    		//System.out.println(tiempoActual/50+"HEY"+tiempoActual/(3*50));
    		g.fillRect(1200-(i*tiempoInicial/5),718,tiempoInicial/5,20);
    	}
    }
    
    public ControllerGame getController(){
    	return controller;
    }
    private void loadScore(){
    	imagenScore = loader.loadImage("/Resources/score.png");
    	
    	numerosArray = new BufferedImage[10];
    	
    	BufferedImage nums = loader.loadImage("/Resources/numbers.png");
    	SpriteSheet ss = new SpriteSheet(nums);
    	
    	numerosArray[0]= ss.grabImage(7, 7, 16, 18);
    	numerosArray[1]= ss.grabImage(41, 7, 14, 18);
    	for(int i = 0;i<8;i++){
    		numerosArray[i+2] = ss.grabImage(71+(32*i), 7, 16, 18);
    	}
    	
    }
    
    private void renderScore(Graphics g){
    	g.drawImage(imagenScore, 110, 710,this);
    	puntaje = rana.getPuntaje();
    	score = String.valueOf(puntaje);
    	for(int i = 0; i < score.length();i++){
    		if(Integer.parseInt(score.charAt(score.length()-1)+"")!=5){
    			g.drawImage(numerosArray[Integer.parseInt(score.charAt(i)+"")], 200+(30*i), 713, this);
    		}
    	}
    }
    
	
    /*
     * Render overall game components.
     */
    public void render(){
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null){
            createBufferStrategy(3);
            return;
        }
        try{
        Graphics g = bs.getDrawGraphics();
        //Rendering the game
        g.drawImage(background,0,0,1366, 768, this);
        renderTurtles(g);
        renderWoods(g);
		g.drawImage(frogSelectedSprite, rana.getX(), rana.getY(), this);
		g.setColor(Color.BLUE);
		//g.fillRect(rana.getX(), rana.getY(), 40, 31);
		renderCars(g);
		renderLlegada(g);
		renderVidas(g);
		renderTime(g);
		renderScore(g);
		
		/*try{
            Thread.sleep(20);
        }catch(Exception e){
            e.printStackTrace();
        }*/
        //End rendering the game
        g.dispose();
        bs.show();
        }
        catch(java.lang.IllegalStateException e){}
    }

}

