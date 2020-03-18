
public class SupriseAttack implements Attack {
	/*-------------------------------------------------------------------
	special attack for Theif class.
	---------------------------------------------------------------------*/
	@Override
	public void attack(DungeonCharacter hero, DungeonCharacter opponent) {
		double surprise = Math.random();
		if (surprise <= .4)
		{
			System.out.println("Surprise attack was successful!\n" +
								hero.getName() + " gets an additional turn.");
			hero.attack(opponent);
			hero.attack(opponent);
		}//end surprise
		else if (surprise >= .9)
		{
			System.out.println("Uh oh! " + opponent.getName() + " saw you and" +
								" blocked your attack!");
		}
		else
		    hero.attack(opponent);

		
	}

}
