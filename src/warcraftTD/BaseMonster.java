package warcraftTD;

public class BaseMonster extends Monster {

	public BaseMonster(Position position) {
		super(position);
	}
	
	/**
	 * Affiche un monstre qui change de couleur au cours du temps
	 * Le monstre est represente par un cercle de couleur bleue ou grise
	 */
	public void draw() {
		StdDraw.setPenColor(StdDraw.BLUE);
		StdDraw.filledCircle(position.x, position.y, 0.01);
	}
}
