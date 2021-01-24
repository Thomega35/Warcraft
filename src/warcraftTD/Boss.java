package warcraftTD;

public class Boss extends Monster {

	public Boss(World w, Position p) {
		super(w, p);
		this.hp = 2200;
		this.goldValue = 90;
		this.flying = false;
		this.speed = 0.02;
	}
	
	public void draw() {
		StdDraw.picture(position.x, position.y, "/images/Boss.png", world.getSquareWidth(), world.getSquareHeight());
	}
	

}
