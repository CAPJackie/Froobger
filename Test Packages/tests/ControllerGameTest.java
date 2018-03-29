package tests;



import aplicacion.ControllerGame;
import aplicacion.FroobgerException;
import aplicacion.Player;
import static org.junit.Assert.*;

import org.junit.Test;

public class ControllerGameTest {

	
	ControllerGame controlador = new ControllerGame();
	Player rana = controlador.getFrog();
	
	//setDirection
	@Test
	public void deberiaCambiarLaDireccionDeLaRanaCuandoSeMueveHaciaLaDerecha() {
		controlador.setDirection(2, 0);
		assertSame("derecha",rana.getOrientacion());
	}
	
	@Test
	public void deberiaCambiarLaDireccionDeLaRanaCuandoSeMueveHaciaLaIzquierda(){
		controlador.setDirection(-2, 0);
		assertSame("izquierda", rana.getOrientacion());
	}
	
	@Test
	public void deberiaCambiarLaDireccionDeLaRanaCuandoSeMueveHaciaArriba(){
		controlador.setDirection(0, -2);
		assertSame("arriba", rana.getOrientacion());
	}
	
	@Test
	public void deberiaCambiarLaDireccionDeLaRanaCuandoSeMueveHaciaAbajo(){
		controlador.setDirection(0, 2);
		assertSame("abajo", rana.getOrientacion());
	}
	
	
	
	//reset()
	
	@Test
	public void deberiaReiniciarLaPosicionEnXCuandoSeResetea(){
		int posX = rana.getX();
		controlador.setDirection(0, -50);
		rana.mover();
		controlador.reset();
		assertEquals(posX,rana.getX());
	}
	
	@Test
	public void deberiaReiniciarLaPosicionEnYCuandoSeResetea(){
		int posY = rana.getY();
		controlador.setDirection(0, -50);
		rana.mover();
		controlador.reset();
		assertEquals(posY,rana.getY());
	}
	
	@Test
	public void deberiaReiniciarLaAlturaMaximaALaQueHaLlegadoLaRanaCuandoSeResetea(){
		int altura = controlador.getMaximaAltura();
		controlador.setDirection(0, -50);
		controlador.getFrog().mover();
		controlador.reset();
		assertEquals(altura,controlador.getMaximaAltura());
	}
	
	//tick()
	@Test
	public void deberiaLanzarExcepcionCuandoSeAcabaronLasVidas() throws FroobgerException{
		rana.setLives(0);
		try{
			controlador.tick();
			fail("No lanzo la excepcion");
		}
		catch(FroobgerException e){
			assertSame(e.getMessage(),FroobgerException.GAME_OVER);
		}
	}
	
	@Test
	public void deberiaLanzarExcepcionCuandoSeAcabaElTiempo()throws FroobgerException{
		controlador.setRealTime(0);
		try{
			controlador.tick();
			fail("No lanzo la excepcion");
		}
		catch(FroobgerException e){
			assertSame(e.getMessage(),FroobgerException.GAME_OVER);
		}
	}
	
	@Test
	public void deberiaRestarUnSegundoAlHacerUnTick()throws FroobgerException{
		int tInicial = controlador.getRealTime();
		controlador.tick();
		assertEquals(tInicial-1,controlador.getRealTime());
	}
	
	@Test
	public void deberiaSumarElPuntajeCuandoAvanzaHaciaArriba() throws FroobgerException{
		int puntaje = rana.getPuntaje();
		rana.setDirection(0, -25);
		rana.mover();
		controlador.tick();
		assertEquals(puntaje+10,rana.getPuntaje());
	}
	
	@Test
	public void noDeberiaSumarElPuntajeCuandoAvanzaHaciaAbajo() throws FroobgerException{
		int puntaje = rana.getPuntaje();
		rana.setDirection(0, 25);
		rana.mover();
		controlador.tick();
		assertEquals(puntaje,rana.getPuntaje());
	}
	
	@Test
	public void noDeberiaSumarElPuntajeCuandoAvanzaHaciaLaDerecha() throws FroobgerException{
		int puntaje = rana.getPuntaje();
		rana.setDirection(25, 0);
		rana.mover();
		controlador.tick();
		assertEquals(puntaje,rana.getPuntaje());
	}
	
	@Test
	public void noDeberiaSumarElPuntajeCuandoAvanzaHaciaLaIzquierda() throws FroobgerException{
		int puntaje = rana.getPuntaje();
		rana.setDirection(-25, 0);
		rana.mover();
		controlador.tick();
		assertEquals(puntaje,rana.getPuntaje());
	}
}
