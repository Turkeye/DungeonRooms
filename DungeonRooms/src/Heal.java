
public class Heal implements Attack{
	public final int MIN_ADD = 25;
	public final int MAX_ADD = 50;
	/*-------------------------------------------------------------------
	special attack for Sorceress class, heals a random amount of HP between
	25 and 50.
	---------------------------------------------------------------------*/
	@Override
	public void attack(DungeonCharacter hero, DungeonCharacter opponent) {
		int hPoints;

		hPoints = (int)(Math.random() * (MAX_ADD - MIN_ADD + 1)) + MIN_ADD;
		hero.addHitPoints(hPoints);
		System.out.println(hero.getName() + " added [" + hPoints + "] points.\n"
							+ "Total hit points remaining are: "
							+ hero.getHitPoints());
		 System.out.println();
		
	}

}
