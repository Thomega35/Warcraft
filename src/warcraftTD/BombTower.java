package warcraftTD;

public class BombTower extends Tower {

	public BombTower() {
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
	}
	
	public void tir(Monster monster) {
		monster.hp -= this.damage;
	}

}
