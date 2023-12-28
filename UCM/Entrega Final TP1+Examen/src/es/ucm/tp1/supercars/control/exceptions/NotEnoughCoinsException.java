package es.ucm.tp1.supercars.control.exceptions;

// EXCEPCION QUE SALTA CUANDO EL JUGADOR NO TIENE SUFICIENTES MONEDAS PARA EJECUTAR UN COMANDO (wave, grenade, shoot)
public class NotEnoughCoinsException extends CommandExecuteException{
	
	public NotEnoughCoinsException(String descripcion) {
		super(descripcion);
	}
}
