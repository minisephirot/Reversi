package reversi.joueur;

import reversi.etats.Etat;
import reversi.etats.EtatReversi;
import reversi.etats.Jeton;

public class JoueurReversi extends Joueur {
	
	private Jeton j;
	private boolean id;
	
	public JoueurReversi(boolean b){
		if (b){
			this.setJeton(Jeton.J1);	
			this.setId(false);
		}else{
			this.setJeton(Jeton.J2);
			this.setId(true);
		}
	}

	public void jouerReversi(EtatReversi e, int x, int y) {
		e.poserJeton(this,x,y);
	}
	
	

	@Override
	public boolean bloque(Etat e) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void jouer(Etat e) {
		// TODO Auto-generated method stub
	}

	/**
	 * @return the jeton
	 */
	public Jeton getJeton() {
		return j;
	}

	/**
	 * @param the jeton to set
	 */
	public void setJeton(Jeton j) {
		this.j = j;
	}

	/**
	 * @return the id of the player
	 */
	public boolean getId() {
		return id;
	}

	/**
	 * @param set the id of the player
	 */
	public void setId(boolean id) {
		this.id = id;
	}

}
