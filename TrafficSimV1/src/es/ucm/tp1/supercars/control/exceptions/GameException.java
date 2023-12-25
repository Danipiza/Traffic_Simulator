package es.ucm.tp1.supercars.control.exceptions;

// SUPERCLASE DE CommandParseException y CommandExecuteException.
public class GameException extends Exception{ // CLASE DE JAVA

	public GameException (String descripcion) {
		super(descripcion);
	}
}
