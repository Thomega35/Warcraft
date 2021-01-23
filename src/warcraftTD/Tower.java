package warcraftTD;

public abstract class Tower {
	
	int range;
	int damage;
	int attackSpeed;
	int attackDelay;
	int projectileSpeed;
	long startTimeTir;
	
	static int upgradeCost = 40;
	
	boolean upgraded;
	boolean tir;
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
