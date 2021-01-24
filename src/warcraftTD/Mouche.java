package warcraftTD;

public class Mouche extends Monster {

	public Mouche(World w, Position p) {
		super(w, p);
		this.hp = 200;
		this.goldValue = 15;
		this.flying = true;
		this.speed = 0.06;
	}
	
	public void draw() {
		StdDraw.picture(position.x, position.y, "/images/Dragon.png", world.getSquareWidth(), world.getSquareHeight(), rotation);
	}

}
