package warcraftTD;

public class ArcherTower extends Tower {

	public ArcherTower() {
		range = 3;
		damage = 1;
		attackSpeed = 30;
		attackDelay = attackSpeed-1;
		projectileSpeed = 3;
		upgraded = false;
		tir = false;
	}
	
	public void upgrade() {
		this.damage = 30;
		this.attackSpeed = 20;
	}
	
	public void tir(Monster monster) {
		monster.hp -= this.damage;
	}
}
