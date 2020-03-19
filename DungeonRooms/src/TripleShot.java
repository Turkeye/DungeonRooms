
public class TripleShot implements Attack{

	@Override
	public void attack(DungeonCharacter hero, DungeonCharacter opponent) {
		double rand = Math.random();
		int shotPoints = (int)(Math.random() * 10) + 15;
		if (rand < .4)
		{
			System.out.println(hero.getName() + " fires three arrows at once for " + shotPoints * 3
								+ " damage!");
			opponent.subtractHitPoints(shotPoints * 3);
		}//all three shots hit
		else if(rand >= .4 && rand < .7) {
			System.out.println(hero.getName() + " fires two arrows at once for " + shotPoints * 2 + " damage!");
			opponent.subtractHitPoints(shotPoints * 2);
		}//two shots hit
		else
		{
			System.out.println(hero.getName() + " fires one arrow for " + shotPoints + " damage!");
			opponent.subtractHitPoints(shotPoints);
		}//one shot hits
		
	}

}
