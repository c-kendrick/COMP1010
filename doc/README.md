# **Project TPOIC 1: Turn-Based RPG** 

## **What problem your application solves ** 
This turn based RPG designed in Java lets you select your class, race and name and head into dungeons to fight enemies. You unlock equipments to make you stronger. As you play, you must adjust your playthrough to deal with scaling difficulty. There is a range of options to choose from when fighting enemies, and players must be strategic in how they use their Ability Points.

## ** Structure**
Use client.java to run the game. It runs generateEquipments() and the game begins when it asks you for a difficuly level, clan, race and name, which are done by functions - getDifficulty(), getClan() and create(int) which calls askRace() and getName(), create(int) takes in your selected clan as a parameter using output of askRace() and getName() to generate a Character object.

Character extends to the different clans (i.e. Barbarian, Mage, Rogue, and Engineer) which are all subclasses of Character.java. Every clan has unique stats and special abilties, they have special interaction between each other. 

Every Character object also has a Race object within it, which is handled by Race.java. Races are used to create a Pokemon style advantage system (e.g. Elf has an advantage over Orc).

After the player character is set enemies and dungeons are created using generateDungeon() which creates many dungeon, which is a recursive data structure that contains the next dungeon to go to. They also populate the dungeon with enemy Character objects (which are all randomly assigned a clan) for you to fight using an ArrayList of Character objects. The creation of enemies and dungeons is handled by Dungeon.java. After the dungeons are generated, the first dungeon is presented to the player using client.gameDriver function. This is a recursive function that goes through the Dungeon list until null, or the player is dead, or the player quits.

After you beat a dungeon you get gold and new equipments and you get to rest, which restores your Health Points (HP), Ability Points (AP), and damage all to max. Some of the clans also have their own special rests (e.g. Mage regains any lost books, Engineer's built devices are dissassembled). While at rest you have 3 choices - exit game, go to next dungeon or equip your new equipments, done by equip(), this shows your unlocked equipments and asks you to type in the ID of your desired equipment. It then aggregates the stats of your 4 selected equipments and adds it to your character. 

Everything related to equipments is in Equipment.java. generateEquipments(), the function called at the very beginning to create new equipments, has a side effect. Creating equipments using its contructor like how this function does writes the Equipment into allEquipment.csv, this file is read out and print at the end of the game.

