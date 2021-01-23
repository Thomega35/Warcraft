package warcraftTD;

public class ArcherTower extends Tower {
	static int buildCost = 50;

	public ArcherTower(World world, Position position) {
		super(world, position);
		range = 3;
		damage = 1;
		attackSpeed = 0.5;
		projectileSpeed = 3;
		upgraded = false;
	}
	
	public void upgrade() {
		this.damage = 30;
		this.attackSpeed = 3;
		this.upgraded = true;
	}
	
	public void tir(Monster monster) {
		if (System.currentTimeMillis() - startTimeTir >= attackSpeed*1000) {
			startTimeTir = System.currentTimeMillis();
			world.projectiles.add(new Arrow(world, new Position(position.x, position.y), monster));
		}
	}
}
