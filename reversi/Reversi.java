package reversi;

import reversi.etats.EtatReversi;


public class Reversi {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//Plateau de Reversi de 8x8
		EtatReversi er = new EtatReversi(8);
		EtatReversi er2 = new EtatReversi(8);
		System.out.println(er.toString());
		System.out.println(er.equals(er2));
	}

}
