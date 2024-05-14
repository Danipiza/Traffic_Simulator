package control.exceptions;


/**
 * @author DannyP39
 * 
 * (Grenade class)
 * 
 * ENG: Exception class for parser problems of a command
 * ESP: Clase de excepcion para problemas al parsear un comando
 */
@SuppressWarnings("serial")
public class CommandParseException extends GameException{
	
	public CommandParseException (String descripcion) {
		super (descripcion);
	}
}
