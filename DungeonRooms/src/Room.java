/*-------------------------------------------------------------------
room class contains a multitude of optional items. Listed in
fields.
---------------------------------------------------------------------*/
public class Room {
	private int healthpot;
	private int visionpot;
	private int pit;
	private int monster;
	private boolean entrance, exit, hasPillar;
	private int ypos;
	private int xpos;
	private String pillar;
	
	public Room(int xpos, int ypos) {
		this.xpos = xpos;
		this.ypos = ypos;
		
		if(this.entrance == false && this.exit == false) {
			int rand1 = (int)Math.ceil(Math.random() * 100);
			int rand2 = (int)Math.ceil(Math.random() * 100);
			int rand3 = (int)Math.ceil(Math.random() * 100);
			if(rand1 <= 10)
				this.pit = 1;
			else if(rand2 <= 10)
				this.healthpot = 1;
			else if(rand3 <= 10)
				this.visionpot = 1;
			else if(rand3 >= 90) {
				this.visionpot = 1;
				this.healthpot = 1;
			}
			else 
				this.monster = 1;
		}
	}
	public int getYpos() {
		return ypos;
	}
	public int getXpos() {
		return xpos;
	}
	public void setEntrance() {
		this.entrance = true;
		this.visionpot = 0;
		this.healthpot= 0;
		this.pit = 0;
		this.monster = 0;
		this.hasPillar = false;
	}
	public void setExit() {
		this.exit = true;
		this.visionpot = 0;
		this.healthpot = 0;
		this.pit = 0;
		this.monster = 0;
		this.hasPillar = false;
		
	}
	public void setPillar(String pillar) {
		this.hasPillar = true;
		this.pillar = pillar;
	}
	public String getPillar() {
		return pillar;
	}
	public String toString() {
		String res = xpos + ", " + ypos + "\n";
		if(this.ypos == 0)
			res += "* * *\n";
		else { 
			res += "* - *\n";
		}
		if(this.xpos == 0) {
			res += "* ";
		}
		else {
			res += "| ";
		}
		if(this.healthpot == 1) 
			res += "H ";
		else if(this.visionpot == 1)
			res += "V ";
		else if(this.pit == 1)
			res += "P "; 
		else if(this.exit == true)
			res += "O ";
		else if(this.entrance == true)
			res += "I ";
		else if(this.monster == 1)
			res += "X ";
		else if(this.healthpot == 1 && this.visionpot == 1)
			res += "M ";
		else {
			res += "E ";
		}
		if(this.xpos == 4)
			res += "*";
		else {
			res += "|";
		}
		res += "\n* ";
		if(this.ypos == 4)
			res += "* *";
		else {
			res += "- *";
		}
		return res;
	}
	public String getTopRow() {
		String res;
		if(this.ypos == 0)
			res = "* * *";
		else {
			res = "* - *";
		}
		return res;
	}
	public String getMiddleRow() {
		String res; 
		if(this.xpos == 0)
			res = "* " + getMiddleItem() + " |";
		else if(this.xpos == 4)
			res = "| " + getMiddleItem() + " *";
		else {
			res = "| " + getMiddleItem() + " |";
		}
		return res;
	}
	
	public String getBottomRow() {
		String res;
		if(this.ypos == 4)
			res = "* * *";
		else {
			res = "* - *";
		}
		return res;
	}
	
	public String getMiddleItem() {
		String res = "";
		if(this.healthpot == 1) 
			res += "H";
		else if(this.visionpot == 1)
			res += "V";
		else if(this.pit == 1)
			res += "P"; 
		else if(this.exit == true)
			res += "O";
		else if(this.entrance == true)
			res += "I";
		else if(this.monster == 1)
			res += "X";
		else if(this.visionpot == 1 && this.healthpot == 1)
			res += "M";
		else {
			res += "E";
		}
		return res;
	}
	public int getHealthpot() {
		return healthpot;
	}
	public void setHealthpot(int healthpot) {
		this.healthpot = healthpot;
	}
	public int getVisionpot() {
		return visionpot;
	}
	public void setVisionpot(int visionpot) {
		this.visionpot = visionpot;
	}
	public int getPit() {
		return pit;
	}
	public void setPit(int pit) {
		this.pit = pit;
	}
	public int getMonster() {
		return monster;
	}
	public void setMonster(int monster) {
		this.monster = monster;
	}
	public boolean isEntrance() {
		return entrance;
	}
	public void setEntrance(boolean entrance) {
		this.entrance = entrance;
	}
	public boolean isExit() {
		return exit;
	}
	public void setExit(boolean exit) {
		this.exit = exit;
	}
	public boolean getHasPillar() {
		return hasPillar;
	}
	public void setHasPillar(boolean hasPillar) {
		this.hasPillar = hasPillar;
	}
	public void setEmpty() {
		this.healthpot = 0;
		this.monster = 0;
		this.visionpot = 0;
		this.hasPillar = false;
	}
}
