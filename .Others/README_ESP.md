*[English](README.md) ∙ [Español]([README_EXP.md](https://github.com/Danipiza/Traffic_Simulator/files/15306795/README_ESP.md))*

# Traffic Simulator


Este repositorio alberga el código fuente de las prácticas implementadas en el lenguaje de programación Java para las asignaturas de Tecnología de la Programación 1 y 2 (TP1 - TP2). Dentro de este repositorio, se encuentran programas de simulación de tráfico que han sido concebidos con el propósito de poner en práctica los conocimientos adquiridos durante el desarrollo de dichas asignaturas.
:-----



---


## INDEX
1. [Upgrades](#upgrades)
2. [UCM](#ucm)
    - [TP1 - Racing Implementation](#tp1)
    - [TP2 - Traffic Implementation](#tp2)

---

## Upgrades
TODO


---

## UCM

Tecnología de la Programacion 1 y 2. Asignaturas del Departamento _Interdepartamental ISIA / SIC_, perteneciente al segundo curso del grado de ingeniería informática en la Universidad Complutense de Madrid.
:--:

### TP1
En esta asignatura cursamos una introducción a la Programación Orientada a Objetos (POO) profundizando en los fundamentos conceptuales y aplicaciones prácticas de este paradigma. Cubriendo aspectos como la definición y manipulación de clases y objetos, la implementación de herencia para la reutilización de código, el uso de polimorfismo y vinculación dinámica para una flexibilidad de diseño, el manejo de excepciones para garantizar la integridad del programa y las operaciones de entrada/salida para la interacción con datos externos.

#### IMPLEMENTACION

El jugador es un coche circulando en una carretera de tamaño variable, con objetos con los que puede interactuar. El **objetivo** del juego es llegar a la meta en el menor tiempo posible. Hay diferentes niveles variando el tamaño de la carretera, así como el tipo de objetos en la carretera y la frecuencia con la que aparecen.
:--

#### Los OBJETOS en la carretera son los siguientes:
| Objetos | Descripción |
| :---: | :--- |
| Moneda | Al interactuar con el jugador suma 1 moneda |
| obstáculo | Objeto estático con 1 punto de vida, al chocar el jugador pierde |

| AVANZADOS | (Solo aparecen en el nivel _ADVANCED)  |
| :--- | :--- |
| Muro | Igual que Obstáculo pero con más puntos de vida (HP: 3)|
| Turbo | Si el jugador pisa el objeto salta 3 posiciones hacia delante. |
| SuperCoin | El jugador recibe 1000 monedas. (Solo hay 1 en toda la carretera) |
| Camión | Igual que Obstáculo, pero se mueve en dirección contraria al jugador en un carril de la carretera.  |
| Peatón | Igual que Obstáculo, pero se mueve de forma vertical en una columna de la carretera. Al destruir este objeto el jugador pierde sus monedas. |

#### El jugador tiene unas ACCIONES disponibles para ejecutar en el entorno.
| Acciones | Descripción |
| :--- | :--- |
| [h]elp | Imprime el menú de ayuda |
| [i]nfo | Imprime el menú de objetos |
| [n]one \| [] | Avanza por la carretera |
| [q] | Sube un carril y avanza por la carretera |
| [a] | Baja un carril y avanza por la carretera |
| [e]xit | Termina la ejecución |
| [r]eset [\<level\> \<seed\>] | Reinicia la ejecución |
| [t]est| Entra en el modo Test|
| Clear \| 0 | Elimina los objetos de la carretera|

| AVANZADOS | (Solo aparecen en el nivel _ADVANCED)  |
| :--- | :--- |
| [s]hoot | Dispara en su carril quitando 1 HP a lo primero que contacte en la zona visible |
| [g]renade | Lanza una granada en la posición (x, y) de la zona visible |
| [w]ave | Lanza una ola, moviendo todos los objetos de la zona visible wave 1 casilla hacia la izquierda |
| Cheat [1..5] | Elimina todos los objetos de la última columna visible y añade un objeto avanzado aleatorio |



Hay implementado un método de recolección del tiempo record conseguido hasta el momento.

#### MODO DE USO
```SuperCars.java <level: TEST, EASY, HARD, ADVANCED> [<seed: numero entero>]```


> [!CAUTION]
> Para que se imprima correctamente en la terminal, los caracteres son UTF8, asegurate de cambiar la configuracion.



### TP2

EXPLICACION TODO

#### Programa detallado
- Introducción al diseño orientado a objetos.
- Patrones de diseño
- Genericidad y colecciones
- Componentes visuales
- Modelo/vista/controlador

Ejecutar el programa, y añadir un fichero json con los datos del programa, pulsando el boton de archivos de arriba a la izquierda, por ejemplo ex2.json






## TODO REMOVE

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

