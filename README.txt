## JAVA DEFENCE

Un jeu vidéo de Tower Defence en JAVA développé par : Thomas Delapart et Guillaume Pinault (Groupe de TP 3)
Ce Projet a été réalisé dans le cadre de notre formation à l'ESIR pour la matière "Programmation Objet".


###I GÉNÉRATION CHEMIN ALÉATOIRE

Le chemin parcouru par les monstres est généré aléatoirement, seules la case de départ en bas à gauche,
et la case d'arrivée, en haut à droite, sont fixes. 
Pour ce faire, nous utilisons la fonction "init path", avec laquelle, nous créons un tableau de taille [nbSquareX][nbSquareY] puis 
nous plaçons aléatoirement un point toute les 2 lignes. Ensuite, nous relions les points pour former un chemin (en ligne et en colonne).
Cas spécial, si le nombre de cases en hauteur du chemin est pair, pas de point sur l'avant-dernière ligne, le chemin monte de 3 cases et finit en haut à droite.

Une fois que ce premier chemin est généré, le tableau est parcouru de sorte que s'il est possible de dessiner un U à l'envers (bosse)
sur l'une des lignes droites alors le chemin est déformé (voir schéma dans la doc de la fonction). On continue jusqu'à ce que 
le tableau soit entièrement parcouru et que le chemin, soit terminé. 								


###II UTILISATION DU CHEMIN ET AFFICHAGE

Une fois que le chemin est générée, la fonction "initcheckpoints" parcourt le chemin pour déterminer les points par lesquelles vont passer les monstres
puis les ajoute à la liste de checkpoints

De plus, le background (case non-chemin/Arrivée/Départ) sont aussi générés aléatoirement pour un rendu plus agréable.
(Valeurs du tableau board @1 = depart @2 = arrivee @3 = chemin @4 =garden1 @5 = garden2 @6 = garden3 @7 = garden4)

Une fois que l'image de fond a été dessinée une première fois, nous faisons une capture de la fenêtre graphique et n'affichons plus que cette
capture (ImageFond.png) pour de meilleures performances graphiques. 
(Les monstres/Tours/projectiles sont affiches par-dessus cette image).


###III MONSTRES

Il y a trois types de monstres :
-Les voitures, le monstre de base terrestre qui arrive à toutes les vagues non spéciales.
-Les dragons, monstre aérien (ne peut donc être touche que par les tours d'archers et les lances roquettes), qui arrivent aux vagues finissant par 5.
-Les boss, monstre terrestre plus lent, mais avec plus de points de vie, qui arrivent toutes les 10 vagues.
 
 Les monstres se déplacent de checkpoints en checkpoints grâce à une Position qui fait office de vecteur direction entre la position du monstre
 et le checkpoint suivant, ce vecteur est normé pour que le déplacement soit linéaire et est géré avec la variable speed.
 Les monstres sont retirés de la liste s'ils sont tues ou s'ils atteignent l'arrivée.
 

###IV TOURS

Il y a trois types de tours :
-Les tours d'archers, qui tirent en mono-cible, rapidement et peuvent cibler les monstres aériens.
-Les tours à bombes, qui infligent des dégâts de zone, et font plus de dégâts, mais ne peuvent pas cibler les monstres aériens et sont plus lentes.
-Les lances roquettes, qui tirent plus loin et leurs projectiles suivent le monstre quand il se déplace. Ils ciblent aussi l'aérien, mais ont des stats
plus faible.

Les projectiles partent de la tour et ciblent un monstre, mais si le projectile percute une autre monstre au passage alors c'est ce deuxième qui prend
les dégâts. Si le projectile manque toutes les cibles, alors il est supprimé une fois sorti de la fenêtre. 


###V COMMANDES

Commandes : 
 a : Tour d'archer
 b : Tour à bombes
 r : Tour Lance Roquette
 e : Amélioration d'une tour
 s : Lancer la partie
 q : Quitter la partie
 k : Tuer tous les monstres d'une vague
 w : Passer a la vague suivante
 g : Donne 1000 gold au joueur
 

###VI RÉPARTITION 

Toutes les images ont été réalisées à la main, ce qui signifie qu'elles sont libres de droit. (Thomas Delapart)
Le chemin aléatoire et le background aléatoire (Thomas Delapart)
Déplacement des monstres et des projectiles (Thomas Delapart)
Création des monstres et des projectiles (Guillaume Pinault)
Implémentation des commandes clavier et souris (notamment pour positionner les tours) (Guillaume Pinault)
Rendu visuel final du jeu, affichage des infos/ du texte/ des images (Thomas Delapart) (Guillaume Pinault) 
Équilibrage du jeu (Thomas Delapart) (Guillaume Pinault)
