package reversi;

import java.util.ArrayList;
import java.util.Arrays;
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

		//false = noir, true = blanc
		JoueurReversi j1 = new JoueurReversi(false);
		JoueurReversi j2 = new JoueurReversi(true);

		int i = 0;
		int j = 0;
		JoueurReversi joueur;
		Scanner in = new Scanner(System.in); 
		while(i != -1 && j != -1){
			//Determine qui a la main
			if (!er.getTour()){
				joueur = j1;
			}else{
				joueur = j2;
			}
			// Produit les successeurs et affiche les possibilitées
			ArrayList<EtatReversi> suivants = er.successeur(joueur);
			//System.out.println(suivants);
			System.out.println(er.toString());
			//Affiche a qui est le tour
			if (joueur.getJeton().toString().equals("N")){
				System.out.println("Aux noirs de jouer.");
			}else{
				System.out.println("Aux blancs de jouer.");
			}
			System.out.println("P = vos possibilités.");
			//Prends les entréess
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
