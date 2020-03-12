
public class AttackType implements Attack {
	String attackRattle;
	
	public AttackType(String attackRattle) {
		this.attackRattle = attackRattle;
	}
	@Override
	public void attack(DungeonCharacter user, DungeonCharacter opponent) {
		
		
		System.out.println(user.getName() + attackRattle + opponent.getName() + ":");
		
		boolean canAttack;
		int damage;

		canAttack = Math.random() <= user.getChanceToHit();

		if (canAttack)
		{
			damage = (int)(Math.random() * (user.getDamageMax() - user.getDamageMin() + 1))
						+ user.getDamageMin();
			opponent.subtractHitPoints(damage);



			System.out.println();
		}//end if can attack
		else
		{

			System.out.println(user.getName() + "'s attack on " + opponent.getName() +
								" failed!");
			System.out.println();
		}//end else
		
	}

}
