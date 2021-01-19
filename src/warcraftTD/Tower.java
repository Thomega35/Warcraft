package warcraftTD;

public abstract class Tower {
	
	int range;
	int damage;
	int attackSpeed;
	int attackDelay;
	int projectileSpeed;
	boolean upgraded;
	boolean tir;
	
	public abstract void upgrade();
	
	public void tir(Monster monster) {
		
	}
}
