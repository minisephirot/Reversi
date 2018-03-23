package reversi.etats;

import java.util.ArrayList;

import reversi.joueur.JoueurReversi;

public class EtatReversi extends Etat {

	private Jeton[][] plateau;
	private boolean numjoueur;//True = blanc 
	private int tailleplateau;
	protected int[] DifferenceX = { -1,  0,  1, -1, 1, -1, 0, 1 };
	protected int[] DifferenceY = { -1, -1, -1,  0, 0,  1, 1, 1 };
	protected static String JNoir = "N";
	protected static String JBlanc = "B";
	protected ArrayList<Coordonne> possibles;
	protected ArrayList<EtatReversi> successeurs;

	private JoueurReversi jnoir;
	private JoueurReversi jblanc;
	protected Coordonne coupjoue;

	public EtatReversi(int i,JoueurReversi jnoir,JoueurReversi jblanc){
		this.plateau = new Jeton[i][i];
		this.numjoueur = false; // au noir de jouer
		this.tailleplateau = i;
		this.possibles = new ArrayList<Coordonne>();
		this.successeurs = new ArrayList<EtatReversi>();
		//Initialise l'état initial du plateau (les 4 pions au centre)
		this.plateau[3][3] = new Jeton(EtatReversi.JNoir);
		this.plateau[4][4] = new Jeton(EtatReversi.JNoir);
		this.plateau[4][3] = new Jeton(EtatReversi.JBlanc);
		this.plateau[3][4] = new Jeton(EtatReversi.JBlanc);
        this.jnoir=jnoir;
        this.jblanc=jblanc;
        this.successeurs = this.successeur(this.getJoueur());
	}

	public EtatReversi(EtatReversi et,int x,int y,JoueurReversi joueur){
		this.possibles = new ArrayList<Coordonne>();
		plateau=new Jeton[et.plateau.length][et.plateau[0].length];
		for(int i=0;i<et.getPlateau().length;i++){
			for(int j=0;j<et.getPlateau()[0].length;j++){
				if(et.plateau[i][j]==null) {
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
		this.coupjoue = new Coordonne(x, y);
		this.inverserJeton(j.toString(), x, y);
		this.numjoueur=!et.numjoueur;
		this.jblanc = et.jblanc;
		this.jnoir = et.jnoir;
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
					if (this.isPossible(i, j)){
						sb.append("P,");
					}else{
						sb.append("0,");
					}
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
		System.out.println(joueur.getId()+"  joue en ["+x+";"+y+"]");
		if (joueur.getId() != this.numjoueur)
			throw new RuntimeException("Erreur : Joueur joue sans que ce soit sont tour.");
		if (!this.isPossible(x, y))
			throw new RuntimeException("Erreur : Placement interdit.");

		Jeton jeton;
		if(joueur.getId()){
			jeton = new Jeton(EtatReversi.JBlanc	);
		}else{
			jeton = new Jeton(EtatReversi.JNoir);
		}
		this.plateau[x][y] = jeton;
		this.inverserJeton(jeton.toString(), x, y);
		this.numjoueur = !this.numjoueur;

		//Reset les possibilités
		this.possibles.clear();
        // Produit les successeurs et affiche les possibilitées
        this.successeurs = this.successeur(this.getJoueur());
		this.maj();
	}

	public Jeton[][] getPlateau(){
		return this.plateau;
	}

	public Coordonne getCoup(){
		return this.coupjoue;
	}

	public int getSizePlateau() {
		return this.plateau.length;
	}

	public String getCouleurJeton(int x, int y) {
		if(plateau[x][y]==null) {
			return "Vide";
		}else {
			return plateau[x][y].toString();
		}
	}

	public int getNbBlanc(){
		int res=0;
		for(int i=0;i< plateau.length;i++){
			for(int j=0;j<plateau.length;j++){
				if(plateau[i][j]!=null && plateau[i][j].toString()=="B"){
					res++;
				}
			}
		}
		return res;
	}

	public int getNbNoir(){
		int res=0;
		for(int i=0;i< plateau.length;i++){
			for(int j=0;j<plateau.length;j++){
				if(plateau[i][j]!=null && plateau[i][j].toString()=="N"){
					res++;
				}
			}
		}
		return res;
	}

	public boolean getTour(){
		return this.numjoueur;
	}
    
	public JoueurReversi getJoueur(){
		if(this.numjoueur)
			return jblanc;
		return jnoir;
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
				else if (this.plateau[x][y].toString() != color ) adverse = true;
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
				else if (this.plateau[x][y].toString() != color ) inversion.add(this.plateau[x][y]);
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

	public boolean isPossible(int x,int y){
		Coordonne c = new Coordonne(x,y);
		return this.possibles.contains(c);
	}

	public int eval0(JoueurReversi joueur){
		int nbblanc = 0;
		int nbnoir = 0;
		for(int i = 0; i<plateau.length;i++){
			for(int j = 0; j<plateau.length;j++){
				if (this.plateau[i][j] != null){
					if (this.plateau[i][j].toString() == EtatReversi.JBlanc ){
						nbblanc++;
					}else{
						nbnoir++;
					}
				}
			}
		}
		if (joueur.getId()){
			return nbblanc-nbnoir;
		}else{
			return nbnoir-nbblanc;
		}
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
						this.possibles.add(new Coordonne(i, j));
						suivant.add(new EtatReversi(this,i,j,joueur));
					}
				}
			}
		}
		this.maj();
		return suivant;
	}

	public EtatReversi minmax(int profondeur){
		ArrayList<EtatReversi> successeurs = this.successeur(getJoueur());
		int scoremax = Integer.MIN_VALUE;
		EtatReversi res = null;
		for (EtatReversi e : successeurs){
			int score = e.evaluer(profondeur, getJoueur(),0,0);
			if (score >= scoremax){
				res = e;
				scoremax = score;
			}
		}
		return res;
	}

	public boolean isFinal(){
		ArrayList<EtatReversi> successeurs = this.successeur(this.jblanc);
		ArrayList<EtatReversi> successeurs2 = this.successeur(this.jnoir);
		return successeurs.isEmpty() && successeurs2.isEmpty();
	}

	private int evaluer(int profondeur, JoueurReversi joueur,int alpha , int beta) {
		int evalue = this.eval0(joueur);
		//Cas final
		if (this.isFinal()){
			if (evalue > 0){ //On gagne
				return Integer.MAX_VALUE;
			}
			if (evalue < 0){ //On perds
				return Integer.MIN_VALUE;
			}
			return evalue;//Egalité
		}
		//Cas normal
		if (profondeur == 0){
			return evalue;
		}
		ArrayList<EtatReversi> successeurs = this.successeur(getJoueur());
		if (getJoueur().isMachine()){
			int scoremax = Integer.MIN_VALUE;
			for (EtatReversi e : successeurs){
				scoremax = Math.max(scoremax, e.evaluer(profondeur-1, joueur, alpha, beta));
			}
			return scoremax;
		}else{
			int scoremin = Integer.MAX_VALUE;
			for (EtatReversi e : successeurs){
				scoremin = Math.min(scoremin, e.evaluer(profondeur-1, joueur, alpha, beta));
			}
			return scoremin;
		}
	}
	
	private void maj(){
		this.setChanged();
		this.notifyObservers();
	}



}
