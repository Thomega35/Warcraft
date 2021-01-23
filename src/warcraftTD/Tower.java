package warcraftTD;

public abstract class Tower {
	
	int range;
	int damage;
	double attackSpeed;
	long attackDelay;
	int projectileSpeed;
	long startTimeTir;
	
	static int upgradeCost = 40;
	
	boolean upgraded;
	Position position;
	World world;
	
	public Tower(World world,Position position){
		this.position = position;
		this.world = world;
		startTimeTir = System.currentTimeMillis();
	}
	public abstract void upgrade();
	
	public abstract void tir(Monster monster);
}
