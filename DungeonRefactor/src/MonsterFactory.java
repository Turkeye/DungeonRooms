
public class MonsterFactory {
	public Monster createMonster(int choice) {
		
		if (choice == 1) {
			return new Monster("Sargath the Skeleton", 100, 3, .8, .3, 30, 50, 30, 50, " slices his rusty blade at ");
		}
		
		else if(choice == 2) {
			return new Monster("Oscar the Ogre", 200, 2, .6, .1, 30, 50, 30, 50, " slowly swings a club toward's ");
		}
		
		else if(choice == 3) {
			return new Monster("Gnarltooth the Gremlin", 70, 5, .8, .4, 15, 30, 20, 40, " jabs his kris at ");
		}
		
		else {
			System.out.println("Invalid type");
			return null;
		}
	}
}
