# Overview

The point of the project is a step-by-step simulation of a 2D world populated by herbivores and predators. 
In addition to creatures, the world contains resources (grass), which the herbivores feed on, and static
objects with which you can't interact - they just fill the space.

![image](https://github.com/MrSinkaaa/Simulation/assets/75826561/ca81cc07-932b-4538-b335-7c21b54432cd)


## Features:

- Creature actions:
   - 'Herbivores' can either go towards the 'Grass' or eat it;
   - 'Predators' can either go towards the 'Herbivores' or eat them.
- Implemented pathfinding algorithms:
  - 'Breadth-firest search' and 'A* algorithm';
  - Algorithms of the path bypasses all obstacles.
- Render map: 
  - The console renderer displays the current state of the map(entities named by the first letter of the class);
  - The swing renderer displays a beautiful simulation image with control buttons.

