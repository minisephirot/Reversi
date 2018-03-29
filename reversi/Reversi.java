package reversi;

import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

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

		String[] elements = new String[]{"1 Joueur", "2 Joueur", "0 Joueur"};
		JComboBox<String> liste = new JComboBox<String>(elements);
		final JComponent[] inputs = new JComponent[] {
				new JLabel("Nombre de joueurs:"),
				liste,
		};
		int result = JOptionPane.showConfirmDialog(null, inputs, "Initialisation", JOptionPane.PLAIN_MESSAGE);
		if (result == JOptionPane.OK_OPTION) {
		} else {
			System.exit(0);
		}
		JoueurReversi j1 = null;
		JoueurReversi j2 = null;
		//false = noir, true = blanc
		if (liste.getSelectedItem().equals("1 Joueur")){
			 j1 = new JoueurReversi(false,false,0);
			 j2 = new JoueurReversi(true,true,3);
		}
		if (liste.getSelectedItem().equals("2 Joueur")){
			 j1 = new JoueurReversi(false,false,0);
			 j2 = new JoueurReversi(true,false,0);
		}
		if (liste.getSelectedItem().equals("0 Joueur")){
			 j1 = new JoueurReversi(false,true,0);
			 j2 = new JoueurReversi(true,true,1);
		}
		//Plateau de Reversi de 8x8
		EtatReversi er = new EtatReversi(8,j1,j2);
		Affichage af = new Affichage(er);
	}

	public static void testConsole() {
		//false = noir, true = blanc
		JoueurReversi j1 = new JoueurReversi(false,false,0);
		JoueurReversi j2 = new JoueurReversi(true,true,1);
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
