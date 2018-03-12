package reversi.etats;

import java.util.ArrayList;

import reversi.joueur.JoueurReversi;

public class EtatReversi extends Etat {

	private Jeton[][] plateau;
	private boolean numjoueur;
	private int tailleplateau;
	protected int[] DifferenceX = { -1,  0,  1, -1, 1, -1, 0, 1 };
	protected int[] DifferenceY = { -1, -1, -1,  0, 0,  1, 1, 1 };
	protected static String JNoir = "N";
	protected static String JBlanc = "B";
	protected static String Poss = "P";

	public EtatReversi(int i){
		this.plateau = new Jeton[i][i];
		this.numjoueur = false; // au noir de jouer
		this.tailleplateau = i;
		//Initialise l'état initial du plateau (les 4 pions au centre)
		this.plateau[3][3] = new Jeton(EtatReversi.JNoir);
		this.plateau[4][4] = new Jeton(EtatReversi.JNoir);
		this.plateau[4][3] = new Jeton(EtatReversi.JBlanc);
		this.plateau[3][4] = new Jeton(EtatReversi.JBlanc);
	}

	public EtatReversi(EtatReversi et,int x,int y,JoueurReversi joueur){
		plateau=new Jeton[et.plateau.length][et.plateau[0].length];
		for(int i=0;i<et.getPlateau().length;i++){
			for(int j=0;j<et.getPlateau()[0].length;j++){
				if(et.plateau[i][j]==null || et.plateau[i][j].toString() == EtatReversi.Poss) {
					this.plateau[i][j]=null;
				}else {
					if (et.plateau[i][j].toString() == EtatReversi.JNoir) this.plateau[i][j]= new Jeton(EtatReversi.JNoir);
					else this.plateau[i][j] = new Jeton(EtatReversi.JBlanc);
				}
			}
		}
		Jeton j;
		if(joueur.getId()){
			j = new Jeton(EtatReversi.JBlanc);
		}else{
			j = new Jeton(EtatReversi.JNoir);
		}
		this.plateau[x][y] = j;
		this.inverserJeton(j.toString(), x, y);
		this.numjoueur=!et.numjoueur;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("\n");
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
		if (this.plateau[x][y].toString() != EtatReversi.Poss)
			throw new RuntimeException("Erreur : Placement interdit.");
		
		Jeton jeton;
		if(joueur.getId()){
			jeton = new Jeton(EtatReversi.JBlanc);
		}else{
			jeton = new Jeton(EtatReversi.JNoir);
		}
		this.plateau[x][y] = jeton;
		this.inverserJeton(jeton.toString(), x, y);
		this.numjoueur = !this.numjoueur;
		
		//Reset les possibilités
		for (int i = 0; i<this.tailleplateau; i++){
			for (int j = 0; j<this.tailleplateau; j++){
				if (this.plateau[i][j] != null && this.plateau[i][j].toString() == EtatReversi.Poss){
					this.plateau[i][j] = null;
				}
			}
		}
	}

	public Jeton[][] getPlateau(){
		return this.plateau;
	}

	public boolean getTour(){
		return this.numjoueur;
	}

	public boolean isLegal (String color, int a, int b ) {
		if (this.outside(a,b)) return false;
		//On regarde dans chaque directions, si on vois une case de la couleur opposée et une des notres c'est bon.
		for (int i = 0; i < this.DifferenceX.length; i++) {
			boolean adverse = false;
			int x = a; 
			int y = b;
			for (int j = 0; j < this.plateau.length; j++) {
				x += this.DifferenceX[i];
				y += this.DifferenceY[i];
				if (this.outside(x, y)) break; // stop si on est en dehors
				if (this.plateau[x][y] == null ) break;
				else if (this.plateau[x][y].toString() != color && this.plateau[x][y].toString() != EtatReversi.Poss ) adverse = true;
				else if (adverse) return true;
				else break;
			}
		}

		return false;
	}

	public void inverserJeton (String color, int a, int b) {
		ArrayList<Jeton> inversion = new ArrayList<Jeton>();
		// Determiner ce qu'on capture
		for (int i = 0; i < this.DifferenceX.length; i++) {
			// Directions
			int x = a; 
			int y = b;
			for (int d = 0; d < this.plateau.length; d++) {
				x += this.DifferenceX[i];
				y += this.DifferenceY[i];
				if (this.outside(x, y)) break; // stop si on est en dehors
				if (this.plateau[x][y] == null) break;
				else if (this.plateau[x][y].toString() != color && this.plateau[x][y].toString() != EtatReversi.Poss ) inversion.add(this.plateau[x][y]);
				else { // On retrouve notre jeton joué
					for (Jeton token : inversion) token.retourner(); // on capture
					break;
				}
			}
			inversion.clear();
		}
	}

	public boolean outside(int x,int y){
		return (x >= plateau.length || y >= plateau.length || x < 0 || y < 0);
	}

	public ArrayList<EtatReversi> successeur(JoueurReversi joueur){
		ArrayList<EtatReversi> suivant = new ArrayList<EtatReversi>();
		for(int i = 0; i<plateau.length;i++){
			for(int j = 0; j<plateau.length;j++){
				if ( plateau[i][j] == null){
					String  couleur;
					if(joueur.getId()){
						couleur = EtatReversi.JBlanc;
					}else{
						couleur = EtatReversi.JNoir;
					}
					if(this.isLegal(couleur, i,j)){
						System.out.println("x:"+i+ " et y:"+j);
						suivant.add(new EtatReversi(this,i,j,joueur));
						//Ajoute un jeton de lisibilité
						this.plateau[i][j] = new Jeton(EtatReversi.Poss);
					}
				}
			}
		}
		return suivant;
	}


}
