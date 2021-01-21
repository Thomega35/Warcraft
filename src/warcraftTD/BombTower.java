package warcraftTD;

public class BombTower extends Tower {
	static int buildCost = 60;

	public BombTower(Position position) {
		super(position);
		range = 2;
		damage = 50;
		attackSpeed = 1;
		attackDelay = attackSpeed-1;
		projectileSpeed = 2;
		upgraded = false;
		tir = false;
	}
	
	public void upgrade() {
		this.damage = 80;
		this.range = 4;
		this.upgraded = true;
	}
	
	public void tir(Monster monster) {
		monster.hp -= this.damage;
	}

}
