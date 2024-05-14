package control.exceptions;



/**
 * @author DannyP39
 * 
 * (Grenade class)
 * 
 * ENG: Exception class for when the player throws the grenade out of the road
 * ESP: Clase de excepcion para cuando el jugador lanza la granada fuera de la carretera
 */
@SuppressWarnings("serial")
public class InvalidPositionException extends CommandExecuteException {
	
	public InvalidPositionException (String descripcion) {
		super (descripcion);
	}
}
