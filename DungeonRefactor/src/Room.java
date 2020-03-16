
public class Room {
	private int healthpot;
	private int visionpot;
	private int pit;
	private int[] Doors;
	private int entrance, exit, monster;
	
	public Room(Room entrance, Room exit) {
		
		int check = 0;
		for(int i = 0; i < 4; i++) {
			this.Doors[i] = (int)Math.round(Math.random()); //Doors[0] is north, 1 is east, 2 is south, and 3 is west
			if(Doors[i] == 1) {  
				check++;
			}
		}
		if(check == 0)
			Doors[0] = 1;
		
		if(this != entrance && this != exit) {
			int rand1 = (int)Math.random() * 100;
			int rand2 = (int)Math.random() * 100;
			int rand3 = (int)Math.random() * 100;
			if(rand1 <= 10)
				this.healthpot = 1;
			else if(rand2 <= 10)
				this.pit = 1;
			else if(rand3 <= 10)
				this.visionpot = 1;
			else if(rand3 >= 90)
				this.monster = 1;
		}
	}
	
	public void setExit() {
		this.exit = 1;
	}
	
	public String toString() {
		String res = "";
		if(this.Doors[0] == 1)
			res += "* - *\n";
		else { 
			res += "* * *\n";
		}
		if(Doors[3] == 1) {
			res += "| ";
		}
		else {
			res += "* ";
		}
		if(this.healthpot == 1) 
			res += "H ";
		else if(this.visionpot == 1)
			res += "V ";
		else if(this.pit == 1)
			res += "P "; 
		else if(this.exit == 1)
			res += "O ";
		else if(this.entrance == 1)
			res += "I ";
		else if(this.monster == 1)
			res += "X ";
		else {
			res += "E ";
		}
		if(this.Doors[1] == 1)
			res += "|";
		else {
			res += "*";
		}
		res += "\n* ";
		if(this.Doors[2] == 1)
			res += "- *";
		else {
			res += "* *";
		}
		return res;
	}
}
