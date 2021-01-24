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
	
	//Ameliore les statistiques de la tour
	public abstract void upgrade();
	
	//Fonction instanciee dans les classes filles qui gere le tir de chaque tour
	public abstract void tir(Monster monster);
}
