package warcraftTD;

public class ArcherTower extends Tower {

	public ArcherTower() {
		range = 5;
		damage = 20;
		attackSpeed = 2;
		projectileSpeed = 3;
		upgraded = false;
	}
	
	public void upgrade() {
		if(!upgraded)
		this.damage = 30;
		this.attackSpeed = 3;
		upgraded = false;
	}
}
