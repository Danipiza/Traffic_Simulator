package control.exceptions;

// EXCEPCION QUE SALTA CUANDO HAY UN PROBLEMA CON EL PARSEO DE UN COMANDO
public class CommandParseException extends GameException{
	
	public CommandParseException (String descripcion) {
		super (descripcion);
	}
}
