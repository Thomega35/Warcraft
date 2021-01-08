package warcraftTD;

public class ArcherTower extends Tower {

	public ArcherTower() {
		range = 5;
		damage = 1;
		attackSpeed = 2;
		projectileSpeed = 3;
		upgraded = false;
	}
	
	public void upgrade() {
		this.damage = 30;
		this.attackSpeed = 3;
	}
	
	public void tir(Monster monster) {
		
	}
}
