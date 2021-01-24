package warcraftTD;

public class BombTower extends Tower {
	static int buildCost = 60;

	public BombTower(World world, Position position) {
		super(world, position);
		range = 2;
		damage = 150;
		attackSpeed = 1.2;
		projectileSpeed = 0.03;
		upgraded = false;
		targetFlying = false;
	}
	
	
	public void upgrade() {
		this.damage = 200;
		this.range = 3;
		this.upgraded = true;
	}
	
	public void tir(Monster monster) {
		if (System.currentTimeMillis() - startTimeTir >= attackSpeed*1000.0) {
			startTimeTir = System.currentTimeMillis();
			world.projectiles.add(new Bombe(world, new Position(position.x, position.y), monster, damage, projectileSpeed));
		}
	}

}
