package reversi.etats;

import java.util.ArrayList;
import java.util.Arrays;

import reversi.joueur.Joueur;
import reversi.joueur.JoueurReversi;

public class EtatReversi extends Etat {

	private Jeton[][] plateau;
	private boolean numjoueur;
	private int tailleplateau;
	protected int[] DifferenceX = { -1,  0,  1, -1, 1, -1, 0, 1 };
    protected int[] DifferenceY = { -1, -1, -1,  0, 0,  1, 1, 1 };
	
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
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i<this.plateau.length;i++){
			sb.append("[");
			for (int j = 0; j<this.plateau[0].length; j++){
				if (this.plateau[i][j] != null){
					sb.append(this.plateau[i][j].toString()+",");
				}else{
					sb.append("0,");
				}
			}
			sb.setLength(sb.length() - 1);
			sb.append("]\n");
		}
		return sb.toString();
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
	
	public EtatReversi(EtatReversi et,int x,int y,JoueurReversi joueur){
		plateau=new Jeton[et.plateau.length][et.plateau[0].length];
		for(int i=0;i<et.getPlateau().length;i++){
			for(int j=0;j<et.getPlateau()[i].length;j++){
				if(et.plateau[i][j]==null) {
					this.plateau[i][j]=null;
				}else {
					this.plateau[i][j]=et.plateau[i][j];
				}
			}
		}
		this.poserJeton(joueur, x, y);
		this.numjoueur=!et.numjoueur;
		
	}
	
	public boolean isLegal (String color, int a, int b ) {
	    if (!this.inside(a,b)) return false;
	    //On regarde dans chaque directions, si on vois une case de la couleur opposée et une des notres c'est bon.
	    for (int i = 0; i < this.DifferenceX.length; i++) {
	      boolean adverse = false;
	      int x = a; 
	      int y = b;
	      for (int j = 0; j < plateau.length; j++) {
	        x += this.DifferenceX[i];
	        y += this.DifferenceY[i];
	        if (x > plateau.length || y > plateau.length) break; // stop when we end up off the board
	        if (this.plateau[x][y] == null) break;
	        else if (this.plateau[x][y].toString() != color) adverse = true;
	        else if (adverse) return true;
	        else break;
	      }
	    }

	    return false;
	  }
	
	public boolean inside(int x,int y){
		return (x >= plateau.length || y >= plateau.length || x < 0 || y < 0);
	}
	
	public ArrayList<EtatReversi> successeur(JoueurReversi joueur){
		ArrayList<EtatReversi> suivant = new ArrayList<EtatReversi>();
		for(int i = 0; i<plateau.length;i++){
			for(int j = 0; j<plateau.length;j++){
				if ( plateau[i][j] == null){
					if(this.isLegal(joueur.getJeton().toString(), i,j)){
						suivant.add(new EtatReversi(this,i,j,joueur));
					}
				}
			}
		}
		return suivant;
	}
	

}
