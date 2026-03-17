A compact Java turn-based dungeon crawler that blends class tactics and race matchups with equipment progression. It is easy to pick up, deep enough to reward strategic AP use, and intentionally built so new content slots in cleanly.

---

## How to Play

* **Getting Started:** Select your difficulty (Normal recommended), choose a class, enter a name, and proceed through the generated dungeons.
* **Combat:** Battles are turn-based. Each turn, you may perform a free attack or spend Ability Points (AP) to use a class special. 
* **Progression:** Defeat enemies one by one to clear a dungeon. You will earn gold and equipment, then rest to restore Health Points (HP) and AP.
* **Equipment:** Equip up to four items to aggregate their stat bonuses.
* **Tactics:**
  * **Classes:** Each features unique mechanics (e.g., invisibility for the Rogue, device building for the Engineer, and spell interactions for the Mage).
  * **Races:** Combat outcomes are heavily affected by a Pokemon-style race advantage system.

---

## How to Run the Program

1. [Download and run latest build](https://github.com/c-kendrick/COMP1010/releases/tag/Main-jar) or [play in browser here](https://ckendrick-mqu.itch.io/dungeons)
2. Click into the input section at the bottom, type numbers to make your menu choices and press `Enter`.
3. **First-time recommendation:** Select Normal difficulty (Option 2) and the Barbarian class (Option 1). Feel free to experiment.

---

## Technical Notes and Design Choices

The design favors clarity and extensibility so new classes, races, or items can be added without changing the dungeon or combat flow.

* **Object-Oriented Architecture:** Uses inheritance to share core fields and behavior in a base `Character` class, while subclassing handles class-specific stats and abilities.
* **Combat & Structure:** `Dungeon` utilizes a recursive structure that holds an `ArrayList` of enemy `Character` objects, allowing combat logic to treat all enemies uniformly.
* **Data Persistence:** Equipment generation writes entries directly to a CSV file for persistence and end-of-game reporting.

---

## Credits

* **Made by:** Christopher Kendrick, Leif Varapuzhakaran, Ken Armstrong, Max Patel
