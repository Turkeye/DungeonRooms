import java.util.Map;
import java.util.HashMap;

public class AttackFactory {
	private Map<String, AttackType> attacks = new HashMap<String, AttackType>(); 
	
	public AttackType getAttack(String attackType) {
		AttackType attack = attacks.get(attackType);
		if(attack == null) {
			attack = new AttackType(attackType);
			attacks.put(attackType, attack);
		}
		return attack;
	}
}
