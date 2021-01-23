package warcraftTD;

public abstract class Tower {
	
	int range;
	int damage;
	double attackSpeed;
	long attackDelay;
	int projectileSpeed;
	
	static int upgradeCost = 40;
	
	boolean upgraded;
	Position position;
	
	public Tower(Position position){
		this.position = position;
	}
	public abstract void upgrade();
	
	public void tir(Monster monster) {
		
	}
}
