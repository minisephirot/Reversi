package reversi.joueur;

import reversi.etats.Etat;
import reversi.etats.EtatReversi;

public class JoueurReversi extends Joueur {
	
	private boolean id;
	
	public JoueurReversi(boolean b){
		if (!b){
			this.setId(false);
		}else{
			this.setId(true);
		}
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
	
	public void jouerReversi(EtatReversi e, int x, int y){
		e.poserJeton(this, x, y);
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
