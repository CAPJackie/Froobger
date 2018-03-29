package aplicacion;



public class FroobgerException extends Exception {

	private static final long serialVersionUID = 1L;
	public static final String GAME_OVER = "GAME OVER";
	public static final String NIVEL_APROVADO = "";

	public FroobgerException(String message){
		super(message);
	}
}
