package control.exceptions;


/**
 * @author DannyP39
 
 * ENG: Exception class for when the player not have enough money to buy a purchase
 * ESP: Clase de excepcion para cuando el jugador no tiene suficiente dinero para realizar una compra
 */
@SuppressWarnings("serial")
public class NotEnoughCoinsException extends CommandExecuteException{
	
	public NotEnoughCoinsException(String descripcion) {
		super(descripcion);
	}
}
