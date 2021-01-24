Projet Tower Defense par Thomas Delapart et Guillaume Pinault (Groupe de TP 3)

Le chemin parcouru par les monstres est genere aleatoirement, seul la case de depart en bas a gauche,
et la case d arrivee, en haut a droite sont fixe. 
Pour se faire, nous utilisons la fonction init path, avec laquelle, nous creons un tableau de taille [nbSquareX][nbSquareY] puis 
nous plaçons aleatoirement un point toute les 2 lignes. Ensuite nous relions les points pour former un chemin (en ligne et en colonne).
Cas special si le chemin est pair, pas de point sur l avant derniere ligne, le chemin monte de 3 cases et fini en haut à droite.

Une fois que ce premier chemin est genere, le tableau est parcouru de sorte à ce que s'il est possible de dessiner un U à l'envers (bosse), 
sur l'une des lignes droites alors le chemin est deforme (voir schema dans la doc de la fonction). On comptinu jusqu'à ce que 
le tableau soit entierement parcouru, ensuite le chemin final est termine. 																										

Une fois que le chemin est genere, la fonction initcheckpoints parcours le chemin pour determiner les points par lesquelles vont passer les monstres.
Puis les ajoute à la liste de checkpoints

De plus le background (case non chemin/Arrivee/Depart) sont aussi generes aleatoirement pour un rendu plus agreable.

Une fois que tout le fond a ete dessiner une premiere fois, nous faisons une capture de la fenetre graphique et ce ne sera plus que cette
capture (ImageFond.png) qui sera affiche lors du jeu. Pour de meilleures performance graphique. 
(Les monstres/Tours/projectiles sont affiches par dessus cette image)

Il y a trois types de monstres :
-Les voitures, le monstre de base terrestre qui arrive a toutes les vagues non speciales.
-Les dragons, monstre aerien (ne peut donc etre touche que par les tours d'archers et les lances roquettes), qui arrivent aux vagues finissant par 5.
-Les boss, monstre terrestre plus lent mais avec plus de points de vie, qui arrivent toutes les 10 vagues.
 
 Les monstres se deplacent de checkpoints en checkpoints grace a une Position qui fait office de vecteur direction entre la position du monstre
 et le checkpoint suivant, ce vecteur est norme pour que le deplacement soit linéaire est est géré avec la variable speed.
 Les monstres sont retires de la liste si ils sont tues ou si ils atteignent l arrivee.

Il y a trois types de tours :
-Les tours d'archers, qui tirent en monocible, rapidement et peuvent cibler les monstres aeriens.
-Les tours a bombes, qui infligent des degats de zone, et font plus de degat mais ne peuvent pas cibler les monstres aeriens et sont plus lentes.
-Les lances roquettes, qui tirent plus loin et leur projectiles suivent le monstre quand il se deplace. Ils ciblent aussi l'aerien mais ont des stats
plus faible.

Les projectiles partent de la tour et ciblent un monstre, mais si le projectile percute une autre monstre au passage alors c'est ce deuxieme qui prend
les degats. Si le projectile manque toute les cible alors il est supprimé une fois sorti de la fenetre. 

Commandes : 
 a : Tour d'archer
 b : Tour a bombes
 r : Tour Lance Roquette
 e : Amelioration d'une tour
 s : Lancer la partie
 q : Quitter la partie
 k : Tuer tous les monstres d'une vague
 w : Passer a la vague suivante
 g : Donne 1000 gold au joueur

Repartition : 

Toute les images ont été réalisé à la main, ce qui signifie qu'elles sont libres de droit. (Thomas Delapart)
Le chemin aleatoire et le backround aleatoire (Thomas Delapart)
Deplacement des monstres et des projectiles (Thomas Delapart)
Creation des monstres et des projectiles (Guillaume Pinault)
Implementation des commandes clavier et souris (nottament pour positionner les tour) (Guillaume Pinault)
Rendu visuel final du jeu, affichage des infos/ du texte/ des images (Thomas Delapart) (Guillaume Pinault) 
Equilibrage du jeu (Thomas Delapart) (Guillaume Pinault)