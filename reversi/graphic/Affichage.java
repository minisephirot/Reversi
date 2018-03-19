package reversi.graphic;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import reversi.etats.EtatReversi;

@SuppressWarnings("serial")
public class Affichage extends JFrame {
	
	private JPanel plateau;
	private JButton[][] cases;
	private EtatReversi etat;

	
	public Affichage(EtatReversi er) {
		super("Reversi");
		this.etat=er;
		this.setSize(600,600);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setBackground(Color.GREEN);
		
		plateau=new JPanel();
		plateau.setLayout(new GridLayout(etat.getSizePlateau(),etat.getSizePlateau()));
		
		cases=new JButton[etat.getSizePlateau()][etat.getSizePlateau()];
		
		
		for(int i=0;i<etat.getSizePlateau();i++) {
			for(int j=0;j<etat.getSizePlateau();j++) {
				final int k=i;
				final int l=j;
				cases[i][j]=new JButton();
				cases[i][j].addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						etat.getJoueur().jouerReversi(etat,k,l);
						miseAJour();
					}
				});
				plateau.add(cases[i][j]);
			}
		}
		
		this.add(plateau);
		this.setVisible(true);
		miseAJour();
	}
	
	public void miseAJour() {
		for(int i=0;i<etat.getSizePlateau();i++) {
			for(int j=0;j<etat.getSizePlateau();j++) {
				if(etat.getCouleurJeton(i, j)=="N") {
					cases[i][j].setBackground(Color.BLACK);
					cases[i][j].setEnabled(false);
				}else if(etat.getCouleurJeton(i, j)=="B"){
					cases[i][j].setBackground(Color.WHITE);
					cases[i][j].setEnabled(false);
				}else if(etat.isPossible(i, j)) {
					cases[i][j].setBackground(Color.BLUE);
					cases[i][j].setEnabled(true);
				}else {
					cases[i][j].setBackground(Color.GREEN);
					cases[i][j].setEnabled(false);
				}
				
			}
		}
	}

}
