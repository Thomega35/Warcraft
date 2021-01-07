package warcraftTD;

public abstract class Monster {
	// Position du monstre a l'instant t
	Position position;
	// Vitesse du monstre
	private double speed;
	//Points de vie du monstre
	int hp = 20;
	
	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
		this.nextPosition = new Position(position.x + this.world.squareWidth * this.speed/*+ world.squareWidth*speed*/,position.y);
		System.out.println(this.world.squareWidth * this.speed);
	}

	// Position du monstre a l'instant t+1
	Position nextPosition;
	// Boolean pour savoir si le monstre a atteint le chateau du joueur
	boolean reached;
	
	// Compteur de deplacement pour savoir si le monstre a atteint le chateau du joueur
	int checkpoint = 0;
	
	protected World world;
	
	public Monster(World w, Position startp) {
		this.position = startp;
		world = w;
		this.nextPosition = new Position(startp.x + this.world.squareWidth * speed/*+ world.squareWidth*speed*/,startp.y);
	}
	
	/**
	 * Deplace le monstre en fonction de sa vitesse sur l'axe des x et des y et de sa prochaine position.
	 */
	public void move() {
		//TXT
//		prend le checkpoint qui lui correspond dans la liste avec la variable checkpoint
//		prend la position suivante
//		calcule le vecteur entre "position" et "obj"
//	PB	NORMER LE VECTEUR? TODO
//		Si le vecteur mouvement est superieur alors, TP sur checkpoint et ++
//		si plus de checkpoints arrivé
		if (checkpoint < world.checkpoints.size()) {
			Position obj = world.checkpoints.get(checkpoint);
			position = nextPosition;
			Position dist = obj.add(new Position(-position.x,-position.y));
			nextPosition = position.add(dist);
//			if (obj.equals(position)) {
//				System.out.print("[pb]");
//				obj = world.checkpoints.get(checkpoint++);
//			}
			
//			StdDraw.setPenColor(StdDraw.RED);
//			StdDraw.filledCircle(obj.x, obj.y, 0.01);
//			StdDraw.show();
			
//	PB		double norme = /*vec/*/new Position(obj.x*world.squareWidth,obj.y*world.squareHeight).dist(new Position(position.x*world.nbSquareX,position.y*world.nbSquareY)); 
			//TODO
			//Position vecNorme = new Position(vec.x/ norme, vec.y/ norme);
//			System.out.print(norme);
//			System.out.println(vecNorme);
			//System.out.println(world.squareWidth + " " + world.squareHeight);

//Si vec trop grand
			if (/*vecNorme.x >= norme || vecNorme.y >= norme*/true){
				nextPosition = obj;
				checkpoint++;
			}else {
				nextPosition = position.add(dist);
			}
		}else {
			//TODO Arrive
			reached = true;
		}
		/*// Mesure sur quel axe le monstre se dirige.
		double dx = nextPosition.x - position.x;
		double dy = nextPosition.y - position.y;
		double signedSW = dx>0?world.squareWidth:(dx<0?-world.squareWidth:0);
		double signedSH = dy>0?world.squareHeight:(dy<0?-world.squareHeight:0);
		double leftx = 0;
		double lefty = 0;
		int nplus1x = world.myCasex(nextPosition.x + signedSW);
		int nplus1y = world.myCasey(nextPosition.y + signedSH);
		System.out.println("nplus1x = " +nplus1x +" || nplus1y =" + nplus1y);
		StdDraw.filledCircle(nplus1x*world.squareWidth +world.squareWidth/2, nplus1y*world.squareHeight + world.squareHeight/2, 0.01);
//		int nplus1x = (int) ((nextPosition.x + dx)/world.squareWidth>=0?(nextPosition.x + dx*2)/world.squareWidth:-1);
//		int nplus1y = (int) ((nextPosition.y + dy)/world.squareHeight>=0?(nextPosition.y + dy)/world.squareHeight:-1);
		//System.out.println("[nplus1x = " + nplus1x + "][nplus1y = " + nplus1y + "]");
		//if (nplus1x >=0 && nplus1x < world.board.length && nplus1y >= 0 && nplus1y < world.board[0].length && (world.board[nplus1x][nplus1y]==3)) {
		if (nplus1x >= 0 && nplus1y >= 0 && nplus1x < world.board.length && nplus1y < world.board[0].length && world.board[nplus1x][nplus1y] == 3) {
			position.x = nextPosition.x;
			position.y = nextPosition.y;
			nextPosition.x += dx;
			nextPosition.y += dy;
		}else {
			if (dx != 0) {
				lefty = dx;
			}
			if (dy != 0) {
				leftx = -dy;
			}
			if (world.board[(int) ((nextPosition.x + leftx*2)/world.squareWidth)][(int) ((nextPosition.y + lefty*2)/world.squareHeight)]==3) {
				position.x = nextPosition.x;
				position.y = nextPosition.y;
				nextPosition.x += leftx;
				nextPosition.y += lefty;
			}else {
				position.x = nextPosition.x;
				position.y = nextPosition.y;
				nextPosition.x -= leftx;
				nextPosition.y -= lefty;
			}
		}
		if (dy + dx != 0){
			// Mesure la distance a laquelle le monstre a pu se deplacer.
			double ratioX = dx/(Math.abs(dx) + Math.abs(dy));
			double ratioY = dy/(Math.abs(dx) + Math.abs(dy));
			position.x += ratioX * speed;
			position.y += ratioY * speed;
		}*/
		
	}

	//calcule la prochaine direction du monstre
	public void calcul() {
		// TODO ATTENTION BULLSHIT
		/*if (world.board[(int) ((nextPosition.x*2 - position.x)/world.squareWidth)][(int) (nextPosition.y/world.squareWidth)] == 3) {
			nextPosition = new Position(nextPosition.x-position.x,nextPosition.y);
		}*/
		this.nextPosition = new Position(this.position.x + this.world.squareWidth / 2/*+ world.squareWidth*speed*/,this.position.y);
	}
	
	public void update() {
		move();
		draw();
		if(this.hp == 0) {
			this.world.gold += 20;
		}
	}

	/**
	 * Fonction abstraite qui sera instanciee dans les classes filles pour afficher le monstre sur le plateau de jeu.
	 */
	public abstract void draw();
}
