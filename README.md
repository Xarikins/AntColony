# AntColony
###An ant colony simulator, written in Java
The colony consists of a queen and her brood, as well as enemy ants called Bala. 

####Ant types
####The queen: ![The queen ant](https://raw.githubusercontent.com/Xarikins/AntColony/master/images/queen.gif)
The queen is the mother of the colony, and all ants except the bala are born at the center of the colony, where she lives.
When the queen dies, the simulation ends. The queen can die from starvation, old age, or from a bala attack.
The queen produces a new ant on the first turn of each day.
####The forager: ![The forager ant](https://raw.githubusercontent.com/Xarikins/AntColony/master/images/forager.gif)
Foragers find food in areas of the colony that have been explored by scouts. Once they find food, they carry it back to the colony, leaving behind a trail of pheromones. Other foragers follow these pheromone trails, carrying food until the supply is exhausted. If a forager dies while carrying food, it will drop the food where it died.
####The soldier: ![The soldier ant](https://raw.githubusercontent.com/Xarikins/AntColony/master/images/soldier.gif)
A soldier's job is to protect the queen; each soldier will move randomly until it encounters an enemy Bala ant, at which point it will continue fighting it until either the Bala is dead, or the soldier has perished in the attempt.
####The scout: ![The scout ant](https://raw.githubusercontent.com/Xarikins/AntColony/master/images/scout.gif)
Scouts explore new ground in the colony. Soldiers and foragers cannot move into unexplored territory unless a scout has explored it for them first. Scouts move randomly.
####The Bala ![The bala ant](https://raw.githubusercontent.com/Xarikins/AntColony/master/images/bala.gif)
Bala ants are the enemy of the colony, they will kill any non-Bala ant that they come across. If a Bala makes it to the queen and successfully kills her, the simulation is over.

Apart from the queen, all ants have a lifespan of one year.

####The colony
The ants live in a 27x27 square grid, with the queen living in the center. Enemy Bala ants enter the grid from the northwest corner.

Each node in the grid is color coded based on the level of pheromone in the node. As time passes, the pheromone levels in each node will decay. Foragers that have successfully found food will increase the amount of pheromones as they travel through a node.

####How to run
Download the files, and compile and run the SimDriver.java file.
