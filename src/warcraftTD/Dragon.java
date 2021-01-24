package warcraftTD;

import java.awt.Font;

public class Dragon extends Monster {
	Font HP = new Font("Arial", Font.BOLD, 16);

	public Dragon(World w, Position p) {
		super(w, p);
		this.hp = 200;
		this.goldValue = 15;
		this.flying = true;
		this.speed = 0.1;
	}
	
	public void draw() {
		StdDraw.picture(position.x, position.y, "/images/Dragon.png", world.getSquareWidth(), world.getSquareHeight(), rotation);
		StdDraw.setPenColor(StdDraw.BLACK);
		StdDraw.setFont(HP);
		StdDraw.text(position.x, position.y, "" + hp);
	}

}
