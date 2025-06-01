# **Project TPOIC 1: Turn-Based RPG** 

## **What problem your application solves ** 
This turn based RPG designed in Java lets you select your class, race and name and head into dungeons to fight enemies. You unlock equipments to make you stronger. As you play, you must adjust your playthrough to deal with scaling difficulty. There is a range of options on how to fight enemies, and players must be strategic in how they use their Ability Points.

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
The game begins by generating equipment and then generating a Character object for the player. Character extends to the different clans classes (i.e. Barbarian, Mage, Rogue, and Engineer) which are all subclasses of Character.java. Every clan has unique stats and special abilties, they have special combat interactions between each other. Every Character object also has a Race object within it, which is handled by Race.java, and includes functions on returning which race has advantage in a given combat. Races are used to create a Pokemon style advantage system (e.g. Elf has an advantage over Orc).

Then dungeons are generated, handled by Dungeon.java, which is a recursive data structure that contains the next dungeon to go to. They also populate the dungeon with an ArrayList of enemy Character objects (which are all randomly assigned a clan & race). After the dungeons are generated, the first dungeon is presented to the player using client.gameDriver function. This is a recursive function that goes through the Dungeon list until null, or the player is dead, or the player quits.

While the dungeon enemy arraylist is still populated, the player must fight the enemies one by one. When one enemy is defeated, they are removed from the arraylist. For combat, there is a while enemy-has-health loop. During combat, the player must choose their action. They can either attack normally for free, or use an ability point to use a special move. Each of the clans has different special moves, and they work against the other clans differently. For example, the Engineer can build a tracker to prevent the Rogue from going invisible. The Rogue can go invisible, and cannot be attacked while so, while also being able to steal the Mage's spell book to prevent the Mage from doing special abilities. 

After you beat a dungeon you get gold and new equipments and you get to rest, which restores your Health Points (HP), Ability Points (AP), and damage all to max. Some of the clans also have their own special rests (e.g. Mage regains any lost books, Engineer's built devices are dissassembled). Attack, specialAttack, and rest functions all exist in the Character main class, and are overriden in its sub-classes. This is so we can access these functions using only a Character object without having to specify the subtype. 

While at rest you have 3 choices - exit game, go to next dungeon or equip your new equipments, done by equip(), this shows your unlocked equipments and asks you to type in the ID of your desired equipment. It then aggregates the stats (e.g. health, damage etc) of your 4 selected equipments and adds it to your Character's stats. 

Everything related to equipments is in Equipment.java. generateEquipments(), the function called at the very beginning to create new equipments, also has a side effect of writting on the CSV file. Creating equipments using its constructor, like how generateEquipments() does, writes the Equipment into allEquipment.csv, using the appendToCSV function, the CSV file is then loaded and printed out at the end of the game. 

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

## Analysis
We used inheritance to make Barbarian a subclass of Character, mainly to keep shared features like health and damage in one place. This made it easier to manage the common behaviour of different character types without repeating code. We examined other options, like composition where we would be giving Barbarian a Character object instead, but that felt like adding an extra layer we didn’t need. Barbarians aren’t just using characters, they are characters. Using just interfaces wouldn’t work either, since they can’t store shared data, and we'd end up copying the same fields into every class. 

In the Dungeon class, the program stores all enemies in a single ArrayList<Character>. This keeps things simple and flexible, since it lets us treat all characters the same without worrying about their specific types. It also means we can add new types of characters later without changing how the dungeon works. If we had gone with separate lists for each class, or added type-specific logic to Dungeon, it would’ve made the code harder to manage and more repetitive.

## UML Diagram
Please see uml.drawio.png in doc folder