package warcraftTD;

public class ArcherTower extends Tower {
	static int buildCost = 50;

	public ArcherTower(Position position) {
		super(position);
		range = 3;
		damage = 1;
		attackSpeed = 2;
		projectileSpeed = 3;
		upgraded = false;
	}
	
	public void upgrade() {
		this.damage = 30;
		this.attackSpeed = 3;
		this.upgraded = true;
	}
	
	public void tir(Monster monster) {
		monster.hp -= this.damage;
	}
}
