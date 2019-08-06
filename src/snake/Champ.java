/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snake;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
//import java.awt.Image;
//import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Champ extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// Info : Le jeu de Snake classique comporte 32*32 cellules
	public final int largeurDivision = 21;// 21,22
	public final int hauteurDivision = 21;
	public final int largeurPixels = 32 * largeurDivision;
	public final int hauteurPixels = 32 * hauteurDivision;
	public final int nbreDivisionsX = 32;
	public final int nbreDivisionsY = 32;

	private ArrayList<Coordonnees> coordsSerpent;
	private ArrayList<Coordonnees> coordsSerpent2;
	private Coordonnees coordsFruit;
	private ArrayList<Coordonnees> coordsMurs;
	private Moteur moteur;

	// private Toolkit toolkit = Toolkit.getDefaultToolkit();

	public Champ() {
		super();
		this.setPreferredSize(new Dimension(32 * largeurDivision, 32 * hauteurDivision));
	}

	public void paintComponent(Graphics g) {

		if (moteur.getTransition() == Transition.JEU) {

			 g.setColor(Color.black);
			 g.fillRect(0, 0, this.getWidth(), this.getHeight());
			//g.drawImage(new ImageIcon(Champ.class.getResource("sable.jpg")).getImage(), 0, 0, this.getWidth(),
					//this.getHeight(), this);

			g.setColor(Color.blue);

			for (int i = 0; i < coordsSerpent.size() - 1; i++) {
				int x = ((Coordonnees) coordsSerpent.get(i)).getPosX();
				int y = ((Coordonnees) coordsSerpent.get(i)).getPosY();
				 g.fillRect(x * largeurDivision, y * hauteurDivision,
				 largeurDivision, hauteurDivision);
				//g.drawImage(new ImageIcon(Champ.class.getResource("serpent_corps.png")).getImage(), x * largeurDivision,
						//y * hauteurDivision, largeurDivision, hauteurDivision, this);
			}

			 g.setColor(Color.cyan);

			int xTete = ((Coordonnees) coordsSerpent.get(coordsSerpent.size() - 1)).getPosX();
			int yTete = ((Coordonnees) coordsSerpent.get(coordsSerpent.size() - 1)).getPosY();
			g.fillRect(xTete * largeurDivision, yTete * hauteurDivision,
			largeurDivision, hauteurDivision);
			//g.drawImage(new ImageIcon(Champ.class.getResource("serpent_tete.png")).getImage(), xTete * largeurDivision,
					//yTete * hauteurDivision, largeurDivision, hauteurDivision, this);

			if (moteur.getNbJoueurs() == 2) {
				 g.setColor(Color.orange);

				for (int i = 0; i < coordsSerpent2.size() - 1; i++) {
					int x = ((Coordonnees) coordsSerpent2.get(i)).getPosX();
					int y = ((Coordonnees) coordsSerpent2.get(i)).getPosY();
					g.fillRect(x * largeurDivision, y * hauteurDivision,
					 largeurDivision, hauteurDivision);
					//g.drawImage(new ImageIcon(Champ.class.getResource("serpent2_corps.png")).getImage(),
							//x * largeurDivision, y * hauteurDivision, largeurDivision, hauteurDivision, this);
				}

				 g.setColor(Color.yellow);

				int xTete2 = ((Coordonnees) coordsSerpent2.get(coordsSerpent2.size() - 1)).getPosX();
				int yTete2 = ((Coordonnees) coordsSerpent2.get(coordsSerpent2.size() - 1)).getPosY();
				 g.fillRect(xTete2 * largeurDivision, yTete2 *
				 hauteurDivision, largeurDivision, hauteurDivision);
				//g.drawImage(new ImageIcon(Champ.class.getResource("serpent2_tete.png")).getImage(),
						//xTete2 * largeurDivision, yTete2 * hauteurDivision, largeurDivision, hauteurDivision, this);
			}

			g.setColor(Color.red);

			g.fillRect(coordsFruit.getPosX() * largeurDivision,
			coordsFruit.getPosY() * hauteurDivision, largeurDivision,
			hauteurDivision);
			// g.drawImage(new ImageIcon(Champ.class.getResource("fruit_normal.png")).getImage(),
					//coordsFruit.getPosX() * largeurDivision, coordsFruit.getPosY() * hauteurDivision, largeurDivision,
					//hauteurDivision, this);

			g.setColor(Color.gray);

			for (int j = 0; j < coordsMurs.size(); j++) {
				int x = ((Coordonnees) coordsMurs.get(j)).getPosX();
				int y = ((Coordonnees) coordsMurs.get(j)).getPosY();
				g.fillRect(x * largeurDivision, y * hauteurDivision,
				largeurDivision, hauteurDivision);
				// g.drawImage(new ImageIcon(Champ.class.getResource("mur.png")).getImage(), x * largeurDivision,
						//y * hauteurDivision, largeurDivision, hauteurDivision, this);
			}

			if (moteur.isStartPressed()) {
				g.setColor(Color.white);
				g.setFont(new Font("Verdana", Font.BOLD, 25));
				g.drawString("PAUSE", 280, 300);
			}
		}

		if (moteur.getTransition() == Transition.MENU) {
			int x = moteur.getCoordsMenu().getPosX();
			int y = moteur.getCoordsMenu().getPosY();

			if (x == 0) {
				if (y == 0)
					g.drawImage(new ImageIcon(getClass().getClassLoader().getResource("img/menuClassique.png")).getImage(), 0, 0, null);
				else
					g.drawImage(new ImageIcon(getClass().getClassLoader().getResource("img/menu1joueur.png")).getImage(), 0, 0, null);
			} else {
				if (y == 0)
					g.drawImage(new ImageIcon(getClass().getClassLoader().getResource("img/menuRegles.png")).getImage(), 0, 0, null);
				else
					g.drawImage(new ImageIcon(getClass().getClassLoader().getResource("img/menu2joueurs.png")).getImage(), 0, 0, null);
			}

		}

		if (moteur.getTransition() == Transition.GAMEOVER) {

			// Image gameover = toolkit.getImage("C:\\Images\\gameover.png");
			//g.setColor(Color.black);
			//g.fillRect(0, 0, this.getWidth(), this.getHeight());
			g.drawImage(new ImageIcon(getClass().getClassLoader().getResource("img/gameover.png")).getImage(), 0, 0, null);
                        
		}

		if (moteur.getTransition() == Transition.FINNIVEAU) {
			g.drawImage(new ImageIcon(getClass().getClassLoader().getResource("img/finniveau.png")).getImage(), 0, 0, null);
		}
		
		if (moteur.getTransition() == Transition.REGLES) {
			g.drawImage(new ImageIcon(getClass().getClassLoader().getResource("img/regles.png")).getImage(), 0, 0, null);
		}

	}

	public ArrayList<Coordonnees> getCoordsSerpent() {
		return this.coordsSerpent;
	}

	public void setCoordsSerpent(ArrayList<Coordonnees> newCoordsSerpent) {
		this.coordsSerpent = newCoordsSerpent;
	}

	public ArrayList<Coordonnees> getCoordsSerpent2() {
		return this.coordsSerpent2;
	}

	public void setCoordsSerpent2(ArrayList<Coordonnees> newCoordsSerpent) {
		this.coordsSerpent2 = newCoordsSerpent;
	}

	public Coordonnees getCoordsFruit() {
		return this.coordsFruit;
	}

	public void setCoordsFruit(Coordonnees newCoordsFruit) {
		this.coordsFruit = newCoordsFruit;
	}

	public ArrayList<Coordonnees> getCoordsMurs() {
		return coordsMurs;
	}

	public void setCoordsMurs(ArrayList<Coordonnees> coordsMurs) {
		this.coordsMurs = coordsMurs;
	}

	public Moteur getMoteur() {
		return moteur;
	}

	public void setMoteur(Moteur moteur) {
		this.moteur = moteur;
	}

}

