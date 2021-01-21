package warcraftTD;

public abstract class Tower {
	
	int range;
	int damage;
	int attackSpeed;
	int attackDelay;
	int projectileSpeed;
	
	//int buildCost;
	//int upgradeCost;
	
	boolean upgraded;
	boolean tir;
	Position position;
	
	public Tower(Position position){
		this.position = position;
	}
	public abstract void upgrade();
	
	public void tir(Monster monster) {
		
	}
}
