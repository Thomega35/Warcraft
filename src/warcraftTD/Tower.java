package warcraftTD;

public abstract class Tower {
	
	protected int range;
	protected int damage;
	protected double attackSpeed;
	protected double projectileSpeed;
	protected long startTimeTir;
	public boolean targetFlying;
	
	static int upgradeCost = 40;
	
	protected boolean upgraded;
	protected Position position;
	protected World world;
	
	public Tower(World world,Position position){
		this.position = position;
		this.world = world;
		startTimeTir = System.currentTimeMillis();
	}
	
	public abstract void upgrade();
	
	
	public abstract void tir(Monster monster);
}
