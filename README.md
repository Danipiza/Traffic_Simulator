*[ENGLISH](README.md) ∙ [ESPAÑOL](https://github.com/Danipiza/Traffic_Simulator/blob/main/.Others/README_ESP.md)*

# Traffic Simulator

This collection contains the Java code for exercises related to Programming Technology 1 and 2 (TP1 - TP2) courses. Included are programs simulating traffic scenarios, designed to apply the concepts learned throughout these courses.
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

Programming Technology 1 and 2. Subjects of the _Interdepartmental ISIA / SIC_ Department, belonging to the second year of the computer engineering degree at the Complutense University of Madrid.
:--:

### TP1
In this subject, we cover an introduction to Object-Oriented Programming (OOP) by delving into the conceptual foundations and practical applications of this paradigm. Covering aspects such as the definition and manipulation of classes and objects, the implementation of inheritance for code reuse, the use of polymorphism and dynamic binding for design flexibility, exception handling to ensure program integrity, and input/output operations for interaction with external data.

#### IMPLEMENTATION

The player is a car traveling on a road of variable size, with objects they can interact with. The **objective** of the game is to reach the finish line in the shortest time possible. There are different levels varying the size of the road, as well as the type of objects on the road and how frequently they appear.
:--

#### The OBJECTS on the road are as follows:
| Objects | Description |
| :---: | :--- |
| Coin | Upon interaction with the player, it adds 1 coin |
| Obstacle | Static object with 1 life point (HP), colliding with the player causes loss (crash) |

| ADVANCED | (Only appear in the _ADVANCED level)  |
| :--- | :--- |
| Wall | Similar to Obstacle but with more life points (HP: 3) |
| Turbo | If the player runs over the object, it jumps 3 positions forward. |
| SuperCoin | The player receives 1000 coins. (Only one exists on the entire road) |
| Truck | Similar to Obstacle, but it moves in the opposite direction of the player in a lane of the road.  |
| Pedestrian | Similar to Obstacle, but it moves vertically in a road column. Destroying this object causes the player to lose their coins. |

#### The player has ACTIONS available to execute in the environment.
| Actions | Description |
| :--- | :--- |
| [h]elp | Prints the help menu |
| [i]nfo | Prints the objects menu |
| [n]one \| [] | Advances on the road |
| [q] | Moves up one lane and advances on the road |
| [a] | Moves down one lane and advances on the road |
| [e]xit | Ends execution |
| [r]eset [\<level\> \<seed\>] | Resets execution |
| [t]est| Enters Test mode|
| Clear \| 0 | Removes objects from the road|

| ADVANCED | (Only appear in the _ADVANCED level)  |
| :--- | :--- |
| [s]hoot | Shoots in its lane, removing 1 HP from the first object it hits in the visible area |
| [g]renade | Throws a grenade at the position (x, y) of the visible area |
| [w]ave | Sends a wave, moving all objects in the visible area 1 square to the left |
| Cheat [1..5] | Removes all objects from the last visible column and adds a random advanced object |


There is a method implemented to collect the current record time achieved in a file.txt.

#### USAGE
```SuperCars.java <level: TEST, EASY, HARD, ADVANCED> [<seed: integer>]```

> [!CAUTION]
> For correct printing in the terminal, the characters are UTF8, make sure to change the configuration.

### Execution
![execution](https://github.com/Danipiza/Traffic_Simulator/assets/98972125/8f7d5cac-1e82-457d-aac6-acdbcae633c4)

### Finish Line
![finish_line](https://github.com/Danipiza/Traffic_Simulator/assets/98972125/6bb696f4-708a-419f-8bef-9481e7455480)

### TP2

EXPLANATION TODO

#### Detailed Program
- Introduction to object-oriented design.
- Design patterns
- Generics and collections
- Visual components
- Model/view/controller

Run the program and add a JSON file with the program data by clicking the file button at the top left, for example ex2.json.

## TODO REMOVE

### Upgrades in the projects + TP1 & TP2 university practices (+ exam exercises).
This repository has resolved the practices of the 2021-2022 course, with the practical exercises asked in the final exam.
In addition to personal improvements that I have found convenient when documenting and programming the code, and for convenience when executing the program.
Making the code more efficient, reorganizing the classes, and improving the interface has been one of the parts that I liked the most because we had to do
everything squared with a hyphen, limiting the development of the application. In addition, several of the functionalities could be done much better.
But to start with our first object-oriented language, the subject is very well organized.

---
