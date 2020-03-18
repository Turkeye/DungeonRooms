import java.util.Scanner;


public class DungeonAdventure {
	/*-------------------------------------------------------------------
	main method for game. Has two loops, one for repeating if the player
	wants to play again, and one internally to allow the player to continue
	entering rooms and battling monsters until they have all 4 pillars
	of OO and have arrived at the exit. Within the inner loop, it
	will get a player's option, place them in the next room if necessary,
	and events will be handled depending on what is in the next room.
	---------------------------------------------------------------------*/
	public static void main(String[] args)
		{

			Hero theHero;
			Monster theMonster;
		    int option;
	    	Room position;

			do
			{
			    theHero = chooseHero();
			    Dungeon dungeon = new Dungeon(theHero);
			    theHero.setPosition(dungeon.getDungeon()[0][0]);
			    Room[][] dungeonArray = dungeon.getDungeon();
			    option = options(theHero, dungeonArray);
		    	position = theHero.getPosition();
			    while(!theHero.getPosition().isExit() && !theHero.getHasAllPillars()) {
			    	switch(option) {
			    	case 1: theHero.setPosition(dungeonArray[position.getYpos()-1][position.getXpos()]);
			    	break;
			    	case 2: theHero.setPosition(dungeonArray[position.getYpos()][position.getXpos()+1]);
			    	break;
			    	case 3: theHero.setPosition(dungeonArray[position.getYpos()+1][position.getXpos()]);
			    	break;
			    	case 4: theHero.setPosition(dungeonArray[position.getYpos()][position.getXpos()-1]);
			    	break;
			    	}
			    	position = theHero.getPosition();
				    System.out.println(position.toString());
				    if(position.getVisionpot() > 0) {
				    	theHero.setNumVisionPotions(theHero.getNumVisionPotions()+1);
				    	System.out.println("You got a vision potion! Vision potions in inventory: " + theHero.getNumVisionPotions());

				    }
				    else if(position.getHealthpot() > 0) {
				    	theHero.setNumHealingPotions(theHero.getNumHealingPotions()+1);
				    	System.out.println("You got a healing potion! Healing potions in inventory: " + theHero.getNumHealingPotions());
				    }
				    else if(position.getPit() > 0) {
				    	int numHPLost = (int)(Math.random() * 20) + 1;
				    	theHero.setHitPoints(theHero.getHitPoints()-numHPLost);
				    	System.out.println("You fell into a pit! Took " + numHPLost + " Damage.");
				    }
				    else if(position.getMonster() > 0) {
				    	Monster monster = generateMonster();
				    	battle(theHero, monster);
				    }
				    else if(position.getHasPillar()) {
				    	String pillar = position.getPillar();
				    	theHero.setPillars(pillar);
				    	dungeon.pillars.remove(pillar);
				    	if(dungeon.pillars.size() == 0) {
				    		System.out.println("You got the pillar " + pillar+ "! 0 pillars left! Head for the exit!");
				    	}
				    	else {
				    		System.out.println("You got the pillar " + pillar+ "! " + dungeon.pillars.size() + " pillars left!");
				    	}
				    }
				    else if(position.isExit()) {
				    	System.out.println("The exit is locked! More Pillars of OO are required!");
				    }
			    	option = options(theHero, dungeonArray);
			    }
			    theMonster = generateMonster();
				battle(theHero, theMonster);

			} while (playAgain());

	    }//end main method
	/*-------------------------------------------------------------------
	chooseHero allows the user to select a hero, creates that hero, and
	returns it.  It utilizes a polymorphic reference (Hero) to accomplish
	this task
	---------------------------------------------------------------------*/
		public static Hero chooseHero()
		{
			int choice;

			System.out.println("Choose a hero:\n" +
						       "1. Warrior\n" +
							   "2. Sorceress\n" +
							   "3. Thief");
			choice = Keyboard.readInt();
			HeroFactory factory = new HeroFactory();
			return factory.createHero(choice);
		}//end chooseHero method

	/*-------------------------------------------------------------------
	generateMonster randomly selects a Monster and returns it.  It utilizes
	a polymorphic reference (Monster) to accomplish this task.
	---------------------------------------------------------------------*/
		public static Monster generateMonster()
		{
			int choice;

			choice = (int)(Math.random() * 3) + 1;

			MonsterFactory factory = new MonsterFactory();
			return factory.createMonster(choice);
		}//end generateMonster method

	/*-------------------------------------------------------------------
	playAgain allows gets choice from user to play another game.  It returns
	true if the user chooses to continue, false otherwise.
	---------------------------------------------------------------------*/
		public static boolean playAgain()
		{
			char again;

			System.out.println("Play again (y/n)?");
			again = Keyboard.readChar();

			return (again == 'Y' || again == 'y');
		}//end playAgain method


	/*-------------------------------------------------------------------
	battle is the actual combat portion of the game.  It requires a Hero
	and a Monster to be passed in.  Battle occurs in rounds.  The Hero
	goes first, then the Monster.  At the conclusion of each round, the
	user has the option of quitting.
	---------------------------------------------------------------------*/
		public static void battle(Hero theHero, Monster theMonster)
		{
			Battle battle = new Battle(theHero, theMonster);
			battle.battle();

		}//end battle method
	/*-------------------------------------------------------------------
	options gets the choice from the player for what to do next by printing 
	the things that the player can do and requesting input. It continues to
	print the options until a player selects a valid option, at which point
	it will perform that option, then ask for another, or go back to the 
	main method and change rooms.
	---------------------------------------------------------------------*/
		public static int options(Hero hero, Room[][] dungeonArray) {

			int optionReturn = 0;
			Scanner scanner = new Scanner(System.in);
			Room position = hero.getPosition();
			while(optionReturn == 0) {
				if(position.getYpos() > 0) {
					System.out.println("Type \"up\" or \"north\" to move 1 room up");
				}
				if(position.getXpos() < 5) {
					System.out.println("Type \"right\" or \"east\" to move 1 room right");
				}
				if(position.getYpos() < 5) {
					System.out.println("Type \"down\" or \"south\" to move 1 room down");
				}
				if(position.getXpos() > 0) {
					System.out.println("Type \"left\" or \"west\" to move 1 room left");
				}
				System.out.println("Type \"hp\" to use health potion. Current Potion amount: " + hero.getNumHealingPotions());
				System.out.println("Type \"vp\" to use vision potion. Current Potion amount: " + hero.getNumVisionPotions());
				System.out.println("Hero Status: " + hero.toString());
				String option = scanner.next();
				if(option.equals("up") || option.equals("north")) {
					optionReturn = 1;
				}
				else if(option.equals("right") || option.equals("east")) {
					optionReturn = 2;
				}
				else if(option.equals("down") || option.equals("south")) {
					optionReturn = 3;
				}
				else if(option.equals("left") || option.equals("west")) {
					optionReturn = 4;
				}
				else if(option.equals("hp") && hero.getNumHealingPotions() > 0) {
					int addPoints = (int)((Math.random() * (15-5)) + 5);
					hero.addHitPoints(addPoints);
					System.out.println("You drank a health potion! Gained " + addPoints + " hit points!");
				}
				else if(option.equals("vp") && hero.getNumVisionPotions() > 0) {
					System.out.println("You drank a vision potion!"); 
					useVisionPotion(dungeonArray, position);
					/*
					if(position.getYpos() > 0 && position.getXpos() > 0) {
						System.out.println(dungeonArray[position.getYpos()-1][position.getXpos()-1].toString());
					}
					if(position.getYpos() > 0) {
						System.out.print(dungeonArray[position.getYpos()-1][position.getXpos()].toString());
					}
					if(position.getYpos() > 0 && position.getXpos() < 5) {
						System.out.print(dungeonArray[position.getYpos()-1][position.getXpos()+1].toString());
					}
					if(position.getXpos() > 0) {
						System.out.println(dungeonArray[position.getYpos()][position.getXpos()-1].toString());
					}
						System.out.print(dungeonArray[position.getYpos()][position.getXpos()].toString());
					if(position.getXpos() < 5) {
						System.out.print(dungeonArray[position.getYpos()][position.getXpos()+1].toString());
					}
					if(position.getYpos() < 5 && position.getXpos() > 0) {
						System.out.println(dungeonArray[position.getYpos()+1][position.getXpos()-1].toString());
					}
					if(position.getYpos() < 5) {
						System.out.print(dungeonArray[position.getYpos()-1][position.getXpos()].toString());
					}
					if(position.getYpos() < 5 && position.getXpos() < 5) {
						System.out.print(dungeonArray[position.getYpos()+1][position.getXpos()+1].toString());
					} */
				}
				else {
					System.out.println("invalid option.");
				}
			}
			//scanner.close();
			return optionReturn;
		}
		 public static void useVisionPotion(Room[][] dung, Room room) {
		    	int xpos = room.getXpos();
		    	int ypos = room.getYpos();
		    	if(xpos == 0 && ypos > 0 && ypos < 4) {
		    		System.out.println(dung[xpos][ypos-1].getTopRow() + " " + room.getTopRow() + " " + dung[xpos][ypos+1].getTopRow());
		    		System.out.println(dung[xpos][ypos-1].getMiddleRow() + " " + room.getMiddleRow() + " " + dung[xpos][ypos+1].getMiddleRow());
		    		System.out.println(dung[xpos][ypos-1].getBottomRow() + " " + room.getBottomRow() + " " + dung[xpos][ypos+1].getBottomRow());
		    		System.out.println(dung[xpos+1][ypos-1].getTopRow() + " " + dung[xpos+1][ypos].getTopRow() + " " + dung[xpos+1][ypos+1].getTopRow());
		    		System.out.println(dung[xpos+1][ypos-1].getMiddleRow() + " " + dung[xpos+1][ypos].getMiddleRow() + " " + dung[xpos+1][ypos+1].getMiddleRow());
		    		System.out.println(dung[xpos+1][ypos-1].getBottomRow() + " " + dung[xpos+1][ypos].getBottomRow() + " " + dung[xpos+1][ypos+1].getBottomRow());
		    	}
		    	else if(xpos == 0 && ypos == 4) {
		    		System.out.println(dung[xpos][ypos-1].getTopRow() + " " + room.getTopRow());
		    		System.out.println(dung[xpos][ypos-1].getMiddleRow() + " " + room.getMiddleRow());
		    		System.out.println(dung[xpos][ypos-1].getBottomRow() + " " + room.getBottomRow());
		    		System.out.println(dung[xpos+1][ypos-1].getTopRow() + " " + dung[xpos+1][ypos].getTopRow());
		    		System.out.println(dung[xpos+1][ypos-1].getMiddleRow() + " " + dung[xpos+1][ypos].getMiddleRow());
		    		System.out.println(dung[xpos+1][ypos-1].getBottomRow() + " " + dung[xpos+1][ypos].getBottomRow());
		    	}
		    	else if(xpos > 0 && ypos == 0 && xpos < 4) {
		    		System.out.println(dung[xpos-1][ypos].getTopRow() + " " + dung[xpos-1][ypos+1].getTopRow());
		    		System.out.println(dung[xpos-1][ypos].getMiddleRow() + " " + dung[xpos-1][ypos+1].getMiddleRow());
		    		System.out.println(dung[xpos-1][ypos].getBottomRow() + " " + dung[xpos-1][ypos+1].getBottomRow());
		    		System.out.println(room.getTopRow() + " " + dung[xpos][ypos+1].getTopRow());
		    		System.out.println(room.getMiddleRow() + " " + dung[xpos][ypos+1].getMiddleRow());
		    		System.out.println(room.getBottomRow() + " " + dung[xpos][ypos+1].getBottomRow());
		    		System.out.println(dung[xpos+1][ypos].getTopRow() + " " + dung[xpos+1][ypos+1].getTopRow());
		    		System.out.println(dung[xpos+1][ypos].getMiddleRow() + " " + dung[xpos+1][ypos+1].getMiddleRow());
		    		System.out.println(dung[xpos+1][ypos].getBottomRow() + " " + dung[xpos+1][ypos+1].getBottomRow());
		    	}
		    	else if(xpos > 0 && ypos > 0 && ypos < 4) {
		    		System.out.println(dung[xpos-1][ypos-1].getTopRow() + " " +dung[xpos-1][ypos].getTopRow() + " " + dung[xpos-1][ypos+1].getTopRow());
		    		System.out.println(dung[xpos-1][ypos-1].getMiddleRow() + " " +dung[xpos-1][ypos].getMiddleRow() + " " + dung[xpos-1][ypos+1].getMiddleRow());
		    		System.out.println(dung[xpos-1][ypos-1].getBottomRow() + " " +dung[xpos-1][ypos].getBottomRow() + " " + dung[xpos-1][ypos+1].getBottomRow());
		    		System.out.println(dung[xpos][ypos-1].getTopRow() + " " + room.getTopRow() + " " + dung[xpos][ypos+1].getTopRow());
		    		System.out.println(dung[xpos][ypos-1].getMiddleRow() + " " + room.getMiddleRow() + " " + dung[xpos][ypos+1].getMiddleRow());
		    		System.out.println(dung[xpos][ypos-1].getBottomRow() + " " + room.getBottomRow() + " " + dung[xpos][ypos+1].getBottomRow());
		    		System.out.println(dung[xpos+1][ypos-1].getTopRow() + " " + dung[xpos+1][ypos].getTopRow() + " " + dung[xpos+1][ypos+1].getTopRow());
		    		System.out.println(dung[xpos+1][ypos-1].getMiddleRow() + " " + dung[xpos+1][ypos].getMiddleRow() + " " + dung[xpos+1][ypos+1].getMiddleRow());
		    		System.out.println(dung[xpos+1][ypos-1].getBottomRow() + " " + dung[xpos+1][ypos].getBottomRow() + " " + dung[xpos+1][ypos+1].getBottomRow());
		    	}
		    	else if(xpos > 0 && ypos == 4) {
		    		System.out.println(dung[xpos-1][ypos-1].getTopRow() + " " + dung[xpos-1][ypos].getTopRow());
		    		System.out.println(dung[xpos-1][ypos-1].getMiddleRow() + " " + dung[xpos-1][ypos].getMiddleRow());
		    		System.out.println(dung[xpos-1][ypos-1].getBottomRow() + " " + dung[xpos-1][ypos].getBottomRow());
		    		System.out.println(dung[xpos][ypos-1].getTopRow() + " " + room.getTopRow());
		    		System.out.println(dung[xpos][ypos-1].getMiddleRow() + " " + room.getMiddleRow());
		    		System.out.println(dung[xpos][ypos-1].getBottomRow() + " " + room.getBottomRow());
		    		System.out.println(dung[xpos+1][ypos-1].getTopRow() + " " + dung[xpos+1][ypos].getTopRow());
		    		System.out.println(dung[xpos+1][ypos-1].getMiddleRow() + " " + dung[xpos+1][ypos].getMiddleRow());
		    		System.out.println(dung[xpos+1][ypos-1].getBottomRow() + " " + dung[xpos+1][ypos].getBottomRow());
		    	}
		    	else if(xpos == 4 && ypos == 4) {
		    		System.out.println(dung[xpos-1][ypos-1].getTopRow() + " " + dung[xpos-1][ypos].getTopRow());
		    		System.out.println(dung[xpos-1][ypos-1].getMiddleRow() + " " + dung[xpos-1][ypos].getMiddleRow());
		    		System.out.println(dung[xpos-1][ypos-1].getBottomRow() + " " + dung[xpos-1][ypos].getBottomRow());
		    		System.out.println(dung[xpos][ypos-1].getTopRow() + " " + room.getTopRow());
		    		System.out.println(dung[xpos][ypos-1].getMiddleRow() + " " + room.getMiddleRow());
		    		System.out.println(dung[xpos][ypos-1].getBottomRow() + " " + room.getBottomRow());
		    	}
		    	
		    }
	
}
