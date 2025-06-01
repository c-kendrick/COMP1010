# **Project TPOIC 1: Turn-Based RPG** 

## **What problem your application solves ** 
This turn based RPG designed in Java lets you select your class, race and name and head into dungeons to fight enemies. You unlock equipments to make you stronger. As you play, you must adjust your playthrough to deal with scaling difficulty. There is a range of options to choose from when fighting enemies, and players must be strategic in how they use their Ability Points.

## **How to run the program**
- Use client.java to run the game.
- run the main function
- You are encouraged to select "Normal" difficulty with "2"
- Type the number and hit enter.
- Choose a class: you are encouraged to use "Barbarian" with "1" as it is the easiest. Mage and Engineer are also very fleshed out, Rogue is difficult and less useful.
- Pick any race, they're all equally good.
- Type in a name and hit enter.
- Continue to read and follow instructions by typing appropriate numbers and hitting enter.

## **Structure**
It runs generateEquipments() and the game begins when it asks you for a difficuly level, clan, race and name, which are done by functions - getDifficulty(), getClan() and create(int) which calls askRace() and getName(), create(int) takes in your selected clan as a parameter using output of askRace() and getName() to generate a Character object.

Character extends to the different clans (i.e. Barbarian, Mage, Rogue, and Engineer) which are all subclasses of Character.java. Every clan has unique stats and special abilties, they have special interaction between each other. 

Every Character object also has a Race object within it, which is handled by Race.java. Races are used to create a Pokemon style advantage system (e.g. Elf has an advantage over Orc).

After the player character is set, enemies and dungeons are created using generateDungeon() which creates many dungeon, which is a recursive data structure that contains the next dungeon to go to. They also populate the dungeon with enemy Character objects (which are all randomly assigned a clan & race) for you to fight using an ArrayList of Character objects. The creation of enemies and dungeons is handled by Dungeon.java. After the dungeons are generated, the first dungeon is presented to the player using client.gameDriver function. This is a recursive function that goes through the Dungeon list until null, or the player is dead, or the player quits.

During combat, the player must choose their action. They can either attack normally for free, or use a ability point to use a special move. Each of the clans has different special moves, and they work against the other clans differently. For example, the Engineer can build a tracker to prevent the Rogue from going invisible. The Rogue can go invisible, and cannot be attacked while so, while also being able to steal the Mage's spell book to prevent the Mage from doing special abilities. 

After you beat a dungeon you get gold and new equipments and you get to rest, which restores your Health Points (HP), Ability Points (AP), and damage all to max. Some of the clans also have their own special rests (e.g. Mage regains any lost books, Engineer's built devices are dissassembled). While at rest you have 3 choices - exit game, go to next dungeon or equip your new equipments, done by equip(), this shows your unlocked equipments and asks you to type in the ID of your desired equipment. It then aggregates the stats of your 4 selected equipments and adds it to your character. 

Everything related to equipments is in Equipment.java. generateEquipments(), the function called at the very beginning to create new equipments, has a side effect. Creating equipments using its contructor like how this function does writes the Equipment into allEquipment.csv, this file is read out and print at the end of the game. This effectively allows you to quit the game and re-start a new dungeon-run, being able to equip from the same inventory from before, as your unlocked items are saved and loaded.

## Task Allocation
Chris: 27.5%
Primary on: Client.java, Character.java, Dungeon.java, Race.java, Barbarian.java
Helped with: Mage.java, Rogue.java, Engineer.java

Leif: 27.5%
Primary on: Equipment.java, UnitTest.java, documentation
Helped with: generally helped with the rest of the program and helped design ideas.

Ken: 25%
Primary on: Mage.java, Engineer.java, design ideas
Helped with: Rogue.java, Client.java, Barbarian.java, Character.java

Max: 20%
Primary on: Rogue.java
Helped with: design ideas