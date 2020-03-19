
public class DisasterPotion implements Attack {
	public boolean used;
	@Override
	public void attack(DungeonCharacter hero, DungeonCharacter opponent) {
		if(!used){

			System.out.println(hero.getName() + " throws a Potion of Disaster at " + opponent.getName() + "!");
			boolean nothingHappened = true;
			if (Math.random() <= .4)
			{
				System.out.println(opponent.getName() + "'s attack speed was decreased to 1!");
				opponent.setAttackSpeed(1);
				nothingHappened = false;
			}//attack speed decrease successful
			if(Math.random() <= .4) {
				System.out.println(opponent.getName() + "'s attack chance decreased to 30%!");
				opponent.setChanceToHit(.3);
				nothingHappened = false;
			}//attack chance decrease successful
			if(Math.random() <= .4) {
				System.out.println(opponent.getName() + "'s healing chance decreased to 10%!");
				((Monster)(opponent)).setChanceToHeal(.1);
				nothingHappened = false;
			}
			if(nothingHappened) {
				System.out.println("but it failed!");
			}
			used = true;
		}
		
	}
}
