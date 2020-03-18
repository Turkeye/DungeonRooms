import java.util.ArrayList;

/**
 * Title: Dungeon.java
 *
 * Description: Driver file for Heroes and Monsters project
 *
 * Copyright:    Copyright (c) 2001
 * Company: Code Dogs Inc.
 * I.M. Knurdy
 *
 * History:
 *  11/4/2001: Wrote program
 *    --created DungeonCharacter class
 *    --created Hero class
 *    --created Monster class
 *    --had Hero battle Monster
 *    --fixed attack quirks (dead monster can no longer attack)
 *    --made Hero and Monster abstract
 *    --created Warrior
 *    --created Ogre
 *    --made Warrior and Ogre battle
 *    --added battleChoices to Hero
 *    --added special skill to Warrior
 *    --made Warrior and Ogre battle
 *    --created Sorceress
 *    --created Thief
 *    --created Skeleton
 *    --created Gremlin
 *    --added game play features to Dungeon.java (this file)
 *  11/27/2001: Finished documenting program
 * version 1.0
 */



/*-------------------------------------------------------------------
default constructor for dungeon will develop a dungeon with 6 special
rooms, with the 4 pillars of OO and an entrance and an exit. The
remainder of the rooms in the 5x5 grid of rooms will have either
a monster, a vision potion, a healing potion, or a pit.
There is a 10% chance that a room will have something other than a
monster, the chances of which are individual from one another.

Recieves: Player hero object
Returns: a 5x5 dungeon of rooms

Calls room constructors and is called by DungeonAdventure class
---------------------------------------------------------------------*/
public class Dungeon
{
	
	private Room[][] dungeon;
	public ArrayList<String> pillars;

    public Dungeon(Hero hero) {
    	Room[][] dungeon = new Room[5][5];
    	for(int i = 0; i < dungeon.length; i++) {
    		for(int j = 0; j < dungeon[i].length; j++) {
    			dungeon[i][j] = new Room(j, i);
    		}
    	}
    	dungeon[0][0].setEntrance();
    	dungeon[4][4].setExit();
        this.pillars = new ArrayList<String>(4);
    	pillars.add("Abstraction");
    	pillars.add("Encapsulation");
    	pillars.add("Inheritance");
    	pillars.add("Polymorphism");
    	dungeon[1][0].setPillar("Abstraction");
    	dungeon[2][1].setPillar("Encapsulation");
    	dungeon[3][3].setPillar("Inheritance");
    	dungeon[4][2].setPillar("Polymorphism");
    	this.dungeon = dungeon;
    }
    
    public Room[][] getDungeon() {
    	return this.dungeon;
    }

    public String toString() {
    	String res = "The dungeon has 25 rooms, 4 Pillars of OO, Pitfalls, Monsters, Vision potions, and health potions.";
    	return res;
    }

    public void useVisionPotion(Room[][] dung, Room room) {
    	int xpos = room.getXpos();
    	int ypos = room.getYpos();
    	if(xpos == 0 && ypos > 0 && ypos < 4) {
    		System.out.println(dung[xpos][ypos-1].toString() + " " + room.toString() + " " + dung[xpos][ypos+1].toString());
    		System.out.println(dung[xpos+1][ypos-1].toString() + " " + dung[xpos+1][ypos].toString() + " " + dung[xpos+1][ypos+1].toString());
    	}
    	else if(xpos == 0 && ypos == 4) {
    		System.out.println(dung[xpos][ypos-1].toString() + " " + room.toString());
    		System.out.println(dung[xpos+1][ypos-1].toString() + " " + dung[xpos+1][ypos].toString());
    	}
    	else if(xpos > 0 && ypos == 0 && xpos < 4) {
    		System.out.println(dung[xpos-1][ypos].toString() + " " + dung[xpos-1][ypos+1].toString());
    		System.out.println(room.toString() + " " + dung[xpos][ypos+1].toString());
    		System.out.println(dung[xpos+1][ypos].toString() + " " + dung[xpos+1][ypos+1].toString());
    	}
    	else if(xpos > 0 && ypos > 0 && ypos < 4) {
    		System.out.println(dung[xpos-1][ypos-1].toString() + " " +dung[xpos-1][ypos].toString() + " " + dung[xpos-1][ypos+1].toString());
    		System.out.println(dung[xpos][ypos-1].toString() + " " + room.toString() + " " + dung[xpos][ypos+1].toString());
    		System.out.println(dung[xpos+1][ypos-1].toString() + " " + dung[xpos+1][ypos].toString() + " " + dung[xpos+1][ypos+1].toString());
    	}
    	else if(xpos > 0 && ypos == 4) {
    		System.out.println(dung[xpos-1][ypos-1].toString() + " " + dung[xpos-1][ypos].toString());
    		System.out.println(dung[xpos][ypos-1].toString() + " " + room.toString());
    		System.out.println(dung[xpos+1][ypos-1].toString() + " " + dung[xpos+1][ypos].toString());
    	}
    	else if(xpos == 4 && ypos == 4) {
    		System.out.println(dung[xpos-1][ypos-1].toString() + " " + dung[xpos-1][ypos].toString());
    		System.out.println(dung[xpos][ypos-1].toString() + " " + room.toString());
    	}
    	
    }
}//end Dungeon class