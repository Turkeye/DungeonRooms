

/**
 * Title: Hero.java
 *
 * Description: Abstract base class for a hierarchy of heroes.  It is derived
 *  from DungeonCharacter.  A Hero has battle choices: regular attack and a
 *  special skill which is defined by the classes derived from Hero.
 *
 *  class variables (all are directly accessible from derived classes):
 *    chanceToBlock -- a hero has a chance to block an opponents attack
 *    numTurns -- if a hero is faster than opponent, their is a possibility
 *                for more than one attack per round of battle
 *
 *  class methods (all are public):
 *    public Hero(String name, int hitPoints, int attackSpeed,
				     double chanceToHit, int damageMin, int damageMax,
					 double chanceToBlock)
	  public void readName()
	  public boolean defend()
	  public void subtractHitPoints(int hitPoints)
	  public void battleChoices(DungeonCharacter opponent)

 * Copyright:    Copyright (c) 2001
 * Company:
 * @author
 * @version 1.0
 */


public class Hero extends DungeonCharacter
{
	private double chanceToBlock;
	private Attack specialAttack;
	private String specialName;
	private int numTurns;
	private int numHealingPotions;
	private int numVisionPotions;
	private String[] pillars  = {null, null, null, null};
	private Room position;

//-----------------------------------------------------------------
//calls base constructor and gets name of hero from user
  public Hero(String name, int hitPoints, int attackSpeed,
				     double chanceToHit, int damageMin, int damageMax,
					 double chanceToBlock, String attackRattle, String specialName, 
					 Attack specialAttack)
  {
	super(name, hitPoints, attackSpeed, chanceToHit, damageMin, damageMax, attackRattle);
	this.chanceToBlock = chanceToBlock;
	this.specialAttack = specialAttack;
	this.specialName = specialName;
	this.numHealingPotions = 0;
	this.numVisionPotions = 0;
	readName();
  }

public double getChanceToBlock() {
	return chanceToBlock;
}

public Attack getSpecialAttack() {
	return specialAttack;
}

public String getSpecialName() {
	return specialName;
}

public int getNumTurns() {
	return numTurns;
}

public int getNumHealingPotions() {
	return numHealingPotions;
}

public void setNumHealingPotions(int numHealingPotions) {
	this.numHealingPotions = numHealingPotions;
}

public int getNumVisionPotions() {
	return numVisionPotions;
}

public void setNumVisionPotions(int numVisionPotions) {
	this.numVisionPotions = numVisionPotions;
}

public String[] getPillars() {
	return pillars;
}

public void setPillars(String pillar) {
	for(int i = 0; i < pillars.length-1; i++){ 
		if(pillars[i] == null) {
			pillars[i] = pillar;
			break;
		}
	}
}

public Room getPosition() {
	return position;
}

public void setPosition(Room position) {
	this.position = position;
}

/*-------------------------------------------------------
readName obtains a name for the hero from the user

Receives: nothing
Returns: nothing

This method calls: nothing
This method is called by: hero constructor
---------------------------------------------------------*/
  public void readName()
  {
	  	String newName;
		System.out.print("Enter character name: ");
		newName = Keyboard.readString();
		this.setName(newName);
  }//end readName method

/*-------------------------------------------------------
defend determines if hero blocks attack

Receives: nothing
Returns: true if attack is blocked, false otherwise

This method calls: Math.random()
This method is called by: subtractHitPoints()
---------------------------------------------------------*/
  public boolean defend()
  {
		return Math.random() <= chanceToBlock;

  }//end defend method

/*-------------------------------------------------------
subtractHitPoints checks to see if hero blocked attack, if so a message
is displayed, otherwise base version of this method is invoked to
perform the subtraction operation.  This method overrides the method
inherited from DungeonCharacter promoting polymorphic behavior

Receives: hit points to subtract
Returns: nothing

This method calls: defend() or base version of method
This method is called by: attack() from base class
---------------------------------------------------------*/
public void subtractHitPoints(int hitPoints)
	{
		if (defend())
		{
			System.out.println(this.getName() + " BLOCKED the attack!");
		}
		else
		{
			super.subtractHitPoints(hitPoints);
		}


	}//end method

/*-------------------------------------------------------
battleChoices will be overridden in derived classes.  It computes the
number of turns a hero will get per round based on the opponent that is
being fought.  The number of turns is reported to the user.  This stuff might
go better in another method that is invoked from this one...

Receives: opponent
Returns: nothing

This method calls: getAttackSpeed()
This method is called by: external sources
---------------------------------------------------------*/
	public void battleChoices(DungeonCharacter opponent)
	{
	    numTurns = getAttackSpeed()/opponent.getAttackSpeed();

		if (numTurns == 0)
			numTurns++;

		System.out.println("Number of turns this round is: " + numTurns);
		
		do
		{
		    System.out.println("1. Attack Opponent");
		    System.out.println("2. " + this.specialName);
		    System.out.print("Choose an option: ");
		    int choice = Keyboard.readInt();
		    if(choice == 1) {
			    attack(opponent);
		    }
			if(choice == 2) {
				this.specialAttack.attack(this, opponent);
				
			} else if(choice < 1 || choice > 2){
				System.out.println("invalid choice!");
		    }

			numTurns--;
			if (numTurns > 0)
			    System.out.println("Number of turns remaining is: " + numTurns);

		} while(numTurns > 0);

	}//end battleChoices
	
	public String toString() {
		String res = this.getName() + " has " + this.getHitPoints() + " hp, " + this.getNumHealingPotions() + " healing potions, " + this.getNumVisionPotions() + " vision potions, and ";
		String[] array = this.getPillars();
		for(int i = 0; i < array.length; i++) {
			if(array[i] != null) 
				res += array[i] + " ";
			if(array == null)
				res += "no pillars of OO have been found";
		}
		return res;
	}

}//end Hero class