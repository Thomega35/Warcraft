package warcraftTD;

public class BombTower extends Tower {
	static int buildCost = 60;

	public BombTower(World world, Position position) {
		super(world, position);
		range = 2;
		damage = 150;
		attackSpeed = 1;
		projectileSpeed = 2;
		upgraded = false;
	}
	
	public void upgrade() {
		this.damage = 200;
		this.range = 4;
		this.upgraded = true;
	}
	
	public void tir(Monster monster) {
		if (System.currentTimeMillis() - startTimeTir >= attackSpeed*1000.0) {
			startTimeTir = System.currentTimeMillis();
			world.projectiles.add(new Arrow(world, new Position(position.x, position.y), monster, damage, projectileSpeed));
		}
	}

}
