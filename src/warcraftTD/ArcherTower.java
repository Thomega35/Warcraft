package warcraftTD;

public class ArcherTower extends Tower {
	static int buildCost = 50;

	public ArcherTower(World world, Position position) {
		super(world, position);
		range = 10;
		damage = 25;
		attackSpeed = 0.3;
		projectileSpeed = 0.4;
		upgraded = false;
	}
	
	public void upgrade() {
		this.damage = 100;
		this.attackSpeed = 0.6;
		this.upgraded = true;
	}
	
	public void tir(Monster monster) {
		if (System.currentTimeMillis() - startTimeTir >= attackSpeed*1000.0) {
			startTimeTir = System.currentTimeMillis();
			world.projectiles.add(new Arrow(world, new Position(position.x, position.y), monster, damage, projectileSpeed));
		}
	}
}
