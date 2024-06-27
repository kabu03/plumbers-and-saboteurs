# Welcome to Pipes in the Desert – A Game by Team Mansaf
Dive into the world of strategic challenges with our Java-based GUI game, where two teams – plumbers and saboteurs – compete in a thrilling environment. Developed by five dedicated students, Pipes in the Desert offers a unique blend of strategy and excitement as teams battle to emerge victorious. Who will dominate the desert?

![image](https://github.com/kabu03/plumbers-and-saboteurs/assets/118374503/512d2833-7eb4-4a7a-9eb1-67ac467f7bc4)

## Storyline
In the vast area of a harsh desert lies an intricate lifeline, a pipe system stretching from the cool, life-giving mountain springs to the cities beyond that are suffering from a water shortage. This system consists of a complex network of simple pipes, and some active elements, such as water pumps (nodes serving as temporary water reservoirs), and cisterns and springs that serve as end-points. Due to a grand conflict, this sophisticated system, designed to harness and distribute the desert's most precious resource, water has unfortunately become the battleground for a high-stakes game of strategy, skill, and sabotage.
In this desolate landscape, two teams emerge with opposing goals. The Plumbers, sent by the city in dire need of water, are the guardians of the flow. Their mission is simple: to ensure the maximum amount of water makes its journey from the springs to the city's cisterns. They navigate the maze of pipes, repair any damage done to the pipes and pumps, and constantly reroute the system to optimize the flow of water.
On the other hand, the Saboteurs, dispatched by a rival entity with heinous motives, aim to ruin the Plumbers' efforts. They can misdirect the water pumps, and puncture the aforementioned pipes, causing leakages, to spill the water into the sands of the desert, depriving the city of its life. Their interference introduces chaos into the order maintained by the Plumbers, turning the game into a dynamic clash of interests.
As the timer starts, the game unfolds on this desert stage with a minimum of two players on each side, embodying the roles of Plumbers and Saboteurs.

## Game Mechanics
- The Plumbers can extend the pipe system with new pipes and pumps manufactured at the cisterns, while the Saboteurs employ their cunning to disrupt the flow.

- Each pump in this elaborate system is a critical node, capable of holding water temporarily and directing it through the maze. However, their reliability is very uncertain, with pumps occasionally failing, halting the flow. The Plumbers, in their quest, can repair these pumps and direct the water efficiently, ensuring the precious resource reaches the cisterns. Pumps can be connected to multiple pipes (each has a specific number of connectable pipes), but they may only have one input and one output. These ingoing and outgoing pipes must be selected by the relevant player, with other connected pipes being closed in the meantime. The pump will not be able to transfer water if the outgoing pipe does not have the free capacity (such as in a scenario where the outgoing pipe is already connected to another active component with water flow). The city's manufacturers are trying to help the plumbers as much as they can, so there will be new pumps manufactured regularly at the cisterns that the plumbers can pick up.

- A new pump can be inserted into the middle of a pipe by cutting the pipe in half and connecting the two free ends to the pump that was collected by the plumber.

- Pipes are the plumbers' friend, as they are the tool that must be utilized to achieve their goal. These pipes are flexible, so they can be disconnected from an active element and connected to another. At the cisterns, new pipes are regularly manufactured, with one end connected to a cistern, and the other end left free. The plumbers must minimize the leakage of pipes (which occurs only if a pipe has a free end and water flowing into it, or if it has been punctured by a saboteur)

- The setting of the game is a very dangerous desert, full of scorpions, therefore, the movement of players is limited to moving on the pipes and pumps. However, the players cannot bypass each other on pipes, they can only move around each other on pumps.

- Victory in this game is measured by water saved or wasted. When the timer stops, the outcome is determined by the amount of water that has successfully reached the city's cisterns versus the volume lost to sabotage. Should the Saboteurs cause more water to be lost than what is saved, they claim a devious win. Conversely, if the Plumbers manage to outmaneuver the sabotage and deliver enough water to the city, they emerge victorious as the champions of hydration.

