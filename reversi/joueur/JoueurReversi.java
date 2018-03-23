package reversi.joueur;

import reversi.etats.Coordonne;
import reversi.etats.Etat;
import reversi.etats.EtatReversi;

public class JoueurReversi extends Joueur {
	
	private boolean id;
	private boolean machine;
	
	public JoueurReversi(boolean b, boolean machine){
		if (!b){
			this.setId(false);
		}else{
			this.setId(true);
		}
		this.machine = machine;
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
	
	public void jouerReversi(EtatReversi e){
		EtatReversi choosed = e.minmax(1);
		Coordonne c = choosed.getCoup();
		e.poserJeton(this, c.getX(), c.getY());
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

	public boolean isMachine() {
		return this.machine;
	}

}
