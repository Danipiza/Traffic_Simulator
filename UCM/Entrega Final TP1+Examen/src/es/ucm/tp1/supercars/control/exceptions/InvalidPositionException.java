package es.ucm.tp1.supercars.control.exceptions;

// EXCEPCION QUE SALTA CUANDO POSICION DE LA CARRETERA PROPORCIONADA POR EL JUGADOR ES INVALIDA (grenade)
public class InvalidPositionException extends CommandExecuteException {
	
	public InvalidPositionException (String descripcion) {
		super (descripcion);
	}
}
