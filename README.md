## Traffic Simulator
---

### Prácticas de TP1 y TP2.

### TrafficSimV1, 2D en la terminal (TP1)
Para que se imprima correctamente en la terminal, los 
caracteres son UTF8, asegurate de cambiar la configuracion.

> [!CAUTION]
> Para ejecutar el programa hay que pasar unos parametros. Uso detallado:
Uso: Super cars <nivel> [<semilla>]
	<nivel>: TEST, EASY, HARD, ADVANCED
	<semilla>: la semilla tiene que ser un numero (es opcional)
Ejemplos: 
- HARD
- Advanced 151

Se puede jugar en la terminal con una serie de comandos
- [h]elp: muestra los comandos
- [i]nfo: imprime informacion del gameobject 
- [n]one | []: avanza
- [q]: sube un fila de la carretera, (tambien avanza)
- [a]: baja un fila de la carretera, (tambien avanza)
- [e]xit: sale del juego
- [r]eset [<level> <seed>]: resetea la partida
- [t]est: modo test
- Clear [0]: limpia la carretera
Comandos avanzados
- Cheat [1..5]:  Elimina todos los elementos de la ultima columna visible, y añade un objeto avanzado alatorio 
- [s]hoot: dispara una bala, quita 1 de vida a los muros
- [g]renade <x> <y>: lanza una granada en la posicion x, y, explotan todos los elementos adyacentes a la posición
- [w]ave: lanza una ola, desplaza todos los elementos visibles del tablero una posicion 

---

### V2, interfaz (TP2)

