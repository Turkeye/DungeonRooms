/*-------------------------------------------------------------------
Hero factory takes the player's choice and returns the appropriate
hero constructor.

Recieves: Player choice
returns: Hero

This method calls hero constructors and is called by the 
GenerateHero method in the DungeonAdventure class.
---------------------------------------------------------------------*/
public class HeroFactory {
	public Hero createHero(int choice) {
		
		if (choice == 1) {
			return new Hero("Warrior", 125, 4, .8, 35, 60, .2, " swings a mighty sword at ", "Crushing Blow ", new CrushingBlow());
		}
		
		else if(choice == 2) {
			return new Hero("Sorceress", 75, 5, .7, 25, 50, .3, " casts a spell of fireball at ", "Heal ", new Heal());
		}
		
		else if(choice == 3) {
			return new Hero("Thief", 75, 6, .8, 20, 40, .5, " swipes his knife at ", "Suprise Attack ", new SupriseAttack());
		}
		
		else {
			System.out.println("Invalid type");
			return null;
	}
	}
}
