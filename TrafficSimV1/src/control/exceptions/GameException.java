package control.exceptions;


/**
 * @author DannyP39
 * 
 * (Grenade class)
 * 
 * ENG: Exception class GameException (father of the commands exceptions)
 * ESP: Clase de excepcion GameException (padre the las excepciones de commandos)
 */
@SuppressWarnings("serial")
public class GameException extends Exception{ // CLASE DE JAVA

	public GameException (String descripcion) {
		super(descripcion);
	}
}
