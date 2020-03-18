/*-------------------------------------------------------------------
default attack interface, abstract flyweight.
---------------------------------------------------------------------*/
public interface Attack {
	public void attack(DungeonCharacter user, DungeonCharacter opponent);
}
