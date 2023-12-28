 package es.ucm.tp1.supercars.control.exceptions;

 // EXCEPTCION QUE SALTA CUANDO HAY PROBLEMAS DE LECTURA CON EL RECORD
public class InputOutputRecordException extends CommandExecuteException{
	
	public InputOutputRecordException(String descripcion) {
		super(descripcion);
	}
}
