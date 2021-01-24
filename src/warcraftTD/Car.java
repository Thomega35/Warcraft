package warcraftTD;

import java.awt.Font;

public class Car extends Monster {
	Font HP = new Font("Arial", Font.BOLD, 16);

	public Car(World w, Position p) {
		super(w, p);
		this.hp = 200;
		this.goldValue = 10;
		this.flying = false;
		this.speed = 0.06;
	}

	@Override
	public void draw() {
		StdDraw.picture(position.x, position.y, "/images/Car.png", world.getSquareWidth(), world.getSquareHeight(),
				rotation);
		StdDraw.setPenColor(StdDraw.BLACK);
		StdDraw.setFont(HP);
		if (position.y + world.getSquareHeight() >= 1) {
			StdDraw.text(position.x, position.y, "" + hp);
		} else {
			StdDraw.text(position.x, position.y + world.getSquareHeight(), "" + hp);
		}
	}

}
