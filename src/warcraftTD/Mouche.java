package warcraftTD;

public class Mouche extends Monster {

	public Mouche(World w, Position p) {
		super(w, p);
		this.hp = 200;
		this.goldValue = 10;
		this.flying = true;
		this.speed = 0.3;
	}
	
	public void draw() {
		StdDraw.picture(position.x, position.y, "/images/Mouche.png", world.squareWidth, world.squareHeight);
	}

}
