package reversi.joueur;

import reversi.etats.Etat;

public abstract class Joueur {
	
	abstract void jouer(Etat e);
	
	abstract boolean bloque(Etat e);

}
