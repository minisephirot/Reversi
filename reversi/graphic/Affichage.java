



package reversi.graphic;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import reversi.etats.EtatReversi;

@SuppressWarnings("serial")
public class Affichage extends JFrame implements Observer {
	
	private JPanel plateau;
	private JPanel score;
	private JLabel nbBlanc;
	private JLabel nbNoir;
	private JButton[][] cases;
	
	private EtatReversi etat;

	
	public Affichage(EtatReversi er) {
		super("Reversi");
		this.etat=er;
		this.etat.addObserver(this);
		this.setSize(1000,800);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setBackground(Color.GREEN);
		
		this.setLayout(new BorderLayout());
		
		plateau=new JPanel();
		plateau.setLayout(new GridLayout(etat.getSizePlateau(),etat.getSizePlateau()));
		plateau.setPreferredSize(new Dimension(800,800));
		
		
		score=new JPanel();
		score.setLayout(new BoxLayout(score, BoxLayout.PAGE_AXIS));
		score.setPreferredSize(new Dimension(200,800));
		
		
		nbBlanc=new JLabel(""+etat.getNbBlanc(),JLabel.CENTER);
		nbNoir=new JLabel(""+etat.getNbNoir(),JLabel.CENTER);
		
		score.add(nbBlanc);
		score.add(nbNoir);
		
		cases=new JButton[etat.getSizePlateau()][etat.getSizePlateau()];
		
		for(int i=0;i<etat.getSizePlateau();i++) {
			for(int j=0;j<etat.getSizePlateau();j++) {
				final int k=i;
				final int l=j;
				cases[i][j]=new JButton();
				cases[i][j].addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						etat.getJoueur().jouerReversi(etat,k,l);
					}
				});
				plateau.add(cases[i][j]);
			}
		}
		
		this.add(plateau,BorderLayout.WEST);
		this.add(score,BorderLayout.EAST);
		this.setVisible(true);
		this.update(this.etat,this);
	}

	public void update(Observable arg0, Object arg1) {
		nbBlanc.setText("Jeton(s) blanc(s) : "+etat.getNbBlanc());
		nbNoir.setText("Jeton(s) Noir(s) : "+etat.getNbNoir());
		
		if(etat.getTour())
			this.setTitle("Reversi - Joueur blanc");
		else
			this.setTitle("Reversi - Joueur noir");
		
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

