 package control.exceptions;

 
 /**
  * @author DannyP39
  * 
  * (Record)
  * 
  * ENG: Exception class for when there are read problems of the record file
  * ESP: Clase de excepcion para cuando hay problemas con la lectura del fichero record
  */
 @SuppressWarnings("serial")
public class InputOutputRecordException extends CommandExecuteException{
	
	public InputOutputRecordException(String descripcion) {
		super(descripcion);
	}
}
