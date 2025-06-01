# **Project TPOIC 1: Turn-Based RPG** 

## **What problem your application solves ** 
This turn based RPG designed in Java, it lets you select your class, race and name and head in dungeons to fight enimies. You gain gold and unlock equipments which can be used to make you stronger.

## ** Structure**
Use client.java to run the game. It runs generateEquipments() and the game begins when it asks you for a difficuly level, clan, race and name, which are done by functions - getDifficulty(), getClan() and create(int) which calls askRace() and getName(), create(int) takes in your selected clan as a parameter uses output of askRace() and getName() to generate a character - player.

Character extends to the different clans(ie Barbarian, Mage, Rogue, Engineer) every clan has unique stats and special abilties, they have special intraction between each other. 

Every character also has a race which is handled by Race.java, races are used to create a Pokemon style advantage system (ie: Elf has an advantage over Orc).

After the player character is set enemies and dungeons are created using generateDungeon() handled which creates a new dungeon, which is a recursive data structure that contains enimies for you to fight, creation of enimies, dungeons etc is handled in Dungeon.csv.

After you beat a dungeon you get gold and new equipments and you get to rest, which restores you HP and other stats to max, while at rest you have 3 choices - exit game, go to next dungeon or equip your new equipments, done by equip(), this shows your unlocked equipments and asks you to type in the ID of your desired equipment. It then aggregates the stats of your 4 selected equipments and adds it to your character.
Everything related to equipments is in Equipment.java

generateEquipments(), the function called at the very beginning to create new equipments, has a side effect. Creating equipments using its contructor like how this function does writes the Equipment into allEquipment.csv, this file is read out and print and the end of the game.

