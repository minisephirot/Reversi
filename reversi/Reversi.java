package reversi;

import java.util.Scanner;

import reversi.etats.EtatReversi;
import reversi.joueur.JoueurReversi;


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

		//false = noir, true = blanc
		JoueurReversi j1 = new JoueurReversi(false);
		JoueurReversi j2 = new JoueurReversi(true);

		int i = 0;
		int j = 0;
		JoueurReversi joueur;
		Scanner in = new Scanner(System.in); 
		while(i != -1 && j != -1){
			//taking value as command line argument.
			if (!er.getTour()){
				joueur = j1;
			}else{
				joueur = j2;
			}
			System.out.printf("Enter x Value:  ");
			i = in.nextInt();
			System.out.printf("Enter y Value:  ");
			j = in.nextInt();
			if (i != -1 && j != -1){
				er.poserJeton(joueur, i, j);
				System.out.println(er.toString());
			}
		}
		in.close();
	}

}
