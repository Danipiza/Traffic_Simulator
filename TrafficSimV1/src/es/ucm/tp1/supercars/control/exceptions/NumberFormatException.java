package es.ucm.tp1.supercars.control.exceptions;

// EXCEPCION QUE SALTA CUANDO EL ELEMENTO PROPORCIONADO POR EL JUGADOR DEBERIA SER NUMERICO Y NO LO ES
public class NumberFormatException extends CommandParseException{
	
	public NumberFormatException (String descripcion) {
		super(descripcion);
	}

}
