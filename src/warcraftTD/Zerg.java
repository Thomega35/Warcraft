package warcraftTD;

import java.awt.Font;

public class Zerg extends Monster {
	Font HP = new Font("Arial", Font.BOLD, 16);
	
	public Zerg(World w, Position p) {
		super(w, p);
		this.hp = 200;
		this.goldValue = 10;
		this.flying = false;
		this.speed = 0.03;
	}

	@Override
	public void draw() {
		StdDraw.picture(position.x, position.y, "/images/Zerg.png", world.getSquareWidth(), world.getSquareHeight(), rotation);
		StdDraw.setPenColor(StdDraw.BLACK);
		StdDraw.setFont(HP);
		StdDraw.text(position.x, position.y, "" + hp);
	}

}
