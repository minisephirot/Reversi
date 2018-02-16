package reversi.etats;

import java.util.Arrays;

import reversi.joueur.JoueurReversi;

public class EtatReversi extends Etat {

	private Jeton[][] plateau;
	private boolean numjoueur;
	private int tailleplateau;
	
	public EtatReversi(int i){
		this.plateau = new Jeton[i][i];
		this.numjoueur = false; // au noir de jouer
		this.tailleplateau = i;
		//Initialise l'état initial du plateau (les 4 pions au centre)
		this.plateau[3][3] = Jeton.J1;
		this.plateau[4][4] = Jeton.J1;
		this.plateau[4][3] = Jeton.J2;
		this.plateau[3][4] = Jeton.J2;
	}

	@Override
	public void read() {
		// TODO Auto-generated method stub
	}

	@Override
	public void write() {
		// TODO Auto-generated method stub
	}

	@Override
	public String toString() {
		return Arrays.deepToString(plateau).replace("], ", "]\n");
	}

	@Override
	public boolean equals(Object o) {
		if (!(o instanceof EtatReversi))
			return false;	
		//C'est le bon objet, on continue les tests
		EtatReversi test = (EtatReversi) o;
		if (this.numjoueur != test.numjoueur)
			return false;
		if (this.tailleplateau != test.tailleplateau)
			return false;
		//test dans le plateau
		for (int i = 0; i<this.tailleplateau; i++){
			for (int j = 0; j<this.tailleplateau; j++){
				boolean p1null = this.plateau[i][j] == null;
				boolean p2null = test.plateau[i][j] == null;
				if (p1null != p2null)
					return false;
				if (!p1null && !p2null){
					if (!this.plateau[i][j].idem(test.plateau[i][j]))
						return false;
				}
			}
		}
		return true;
	}

	public void poserJeton(JoueurReversi joueur,int x, int y) {
		if (joueur.getId() != this.numjoueur)
			throw new RuntimeException("Erreur : Joueur joue sans que ce soit sont tour.");
		if (this.plateau[x][y] != null)
			throw new RuntimeException("Erreur : Joueur joue par dessus un pion.");
		this.plateau[x][y] = joueur.getJeton();
		this.numjoueur = !this.numjoueur;
	}
	
	public Jeton[][] getPlateau(){
		return this.plateau;
	}
	
	public boolean getTour(){
		return this.numjoueur;
	}

}
