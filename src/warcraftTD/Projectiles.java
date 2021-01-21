package warcraftTD;

public abstract class Projectiles {

	Position position;
	Monster monster;
	int degat;
	int speed;
	
	public Projectiles(Position position, Monster monster) {
		this.position = position;
		this.monster = monster;
	}

	public abstract void draw();

	public void move() {
		// TODO Auto-generated method stub
		
	}

	public void update() {
		move();
		draw();
	}
}
