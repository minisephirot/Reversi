package reversi;

import java.util.ArrayList;
import java.util.Scanner;

import reversi.etats.EtatReversi;
import reversi.graphic.Affichage;
import reversi.joueur.JoueurReversi;


public class Reversi {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		  testInterface();
		//testConsole();
	}

	public static void testInterface() {

		//false = noir, true = blanc
		JoueurReversi j1 = new JoueurReversi(false,true);
		JoueurReversi j2 = new JoueurReversi(true,true);
		//Plateau de Reversi de 8x8
		EtatReversi er = new EtatReversi(8,j1,j2);

		Affichage af = new Affichage(er);
	}

	public static void testConsole() {
		//false = noir, true = blanc
		JoueurReversi j1 = new JoueurReversi(false,false);
		JoueurReversi j2 = new JoueurReversi(true,true);
		//Plateau de Reversi de 8x8
		EtatReversi er = new EtatReversi(8,j1,j2);

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
			ArrayList<EtatReversi> suivants = er.successeur(joueur,false);
			System.out.println(er.toString());
			for (EtatReversi e: suivants){
				System.out.println("Coup possible en x:"+e.getCoup().getX() + " et y:"+e.getCoup().getY());
			}
			//Affiche a qui est le tour
			if (!joueur.getId()){
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
				joueur.jouerReversi(er, i, j);
			}

		}
		in.close();

	}

}
