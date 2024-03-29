package warcraftTD;

public class RocketLauncher extends Tower {
	static int buildCost = 60;

	public RocketLauncher(World world, Position position) {
		super(world, position);
		range = 6;
		damage = 25;
		attackSpeed = 0.5;
		projectileSpeed = 0.15;
		upgraded = false;
		targetFlying = true;
	}
	
	@Override
	public void upgrade() {
		this.damage = 40;
		this.attackSpeed = 0.6;
		this.upgraded = true;
	}
	
	@Override
	public void tir(Monster monster) {
		if (System.currentTimeMillis() - startTimeTir >= attackSpeed*1000.0) {
			startTimeTir = System.currentTimeMillis();
			world.projectiles.add(new Rocket(world, new Position(position.x, position.y), monster, damage, projectileSpeed));
		}
	}

}
