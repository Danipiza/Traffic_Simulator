package control.exceptions;

// EXCEPCION QUE SALTA CUANDO HAY UN PROBLEMA CON LA EJECUCION DE UN COMANDO
public class CommandExecuteException extends GameException{
	
	public CommandExecuteException(String descripcion) {
		super(descripcion);
	}
}
