package control.exceptions;


/**
 * @author DannyP39
 
 * ENG: Exception class for incorrect numeric values
 * ESP: Clase de excepcion para valores numericos incorrectos
 */
@SuppressWarnings("serial")
public class NumberFormatException extends CommandParseException{
	
	public NumberFormatException (String descripcion) {
		super(descripcion);
	}

}
