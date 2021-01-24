package warcraftTD;

import java.awt.Font;

public class Boss extends Monster {
	Font HP = new Font("Arial", Font.BOLD, 16);

	public Boss(World w, Position p) {
		super(w, p);
		this.hp = 2400;
		this.goldValue = 80;
		this.flying = false;
		this.speed = 0.02;
	}
	
	@Override
	public void draw() {
		StdDraw.picture(position.x, position.y, "/images/Boss.png", world.getSquareWidth(), world.getSquareHeight(), rotation);
		StdDraw.setPenColor(StdDraw.BLACK);
		StdDraw.setFont(HP);
		if (position.y + world.getSquareHeight() >= 1) {
			StdDraw.text(position.x, position.y, "" + hp);
		} else {
			StdDraw.text(position.x, position.y + world.getSquareHeight()/2, "" + hp);
		}
	}
	

}
