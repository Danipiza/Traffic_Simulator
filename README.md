## Traffic Simulator

---

### Mejoras en los proyectos + prácticas de TP1 y TP2 (+ ejercicios de examen).
Este repositorio tiene las prácticas resueltas del curso 2021-2022, con los ejercicios prácticos que preguntaron en el examen final. 
Ademas de mejoras personales que he visto convenientes a la hora de documentar y programar el código, y para la comodidad a la hora de ejecutar el programa. 
Hacer mas eficiente el código, reorganizar las clases y mejorar la interfaz ha sido una de las partes que mas me ha gustado debido a que teniamos que hacer 
todo cuadriculado con un guión, limitando el desarrollo de la aplicación. Además de que vairas de las funcionalidades se podrían hacer mucho mejor. 
Pero para empezar a con nuestro primer lenguaje orientado a objetos está muy bien organizada la asignatura.

---

### Upgrades in the projects + TP1 & TP2 university practices (+ exam exercices).
This repository has resolved the two practices of the 2021-2022 course, with the practical exercises asked in the final exam of the subject.
In addition to personal improvements that I have seen convenient when documenting and programming the code, and for convenience when executing the program.
Making the code more efficient, reorganizing the classes and improving the interface has been one of the parts that I liked the most because we had to do
everything gridded with a script, limiting the development of the application. In addition, several of the functionalities could be done much better.
But to start with our first object-oriented language, the subject is very well organized.

---

# TODO
- Analizar, cambiar y documentar el codigo
- Mejorar README
- Mejorar V2

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

Ejecutar el programa, y añadir un fichero json con los datos del programa, pulsando el boton de archivos de arriba a la izquierda, por ejemplo ex2.json

