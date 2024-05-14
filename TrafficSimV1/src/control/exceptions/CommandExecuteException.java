package control.exceptions;

// EXCEPCION QUE SALTA CUANDO HAY UN PROBLEMA CON LA EJECUCION DE UN COMANDO
/**
 * @author DannyP39
 * 
 * (Grenade class)
 * 
 * ENG: Exception class for execution command problems
 * ESP: Clase de excepcion para cuando hay problemas de ejecucion de un comando
 */
@SuppressWarnings("serial")
public class CommandExecuteException extends GameException{
	
	public CommandExecuteException(String descripcion) {
		super(descripcion);
	}
}
