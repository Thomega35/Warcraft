package warcraftTD;

public abstract class Tower {
	
	int range;
	int damage;
	int attackSpeed;
	int projectileSpeed;
	boolean upgraded;
	
	public abstract void upgrade();
	
	public void tir() {
		
	}
}
