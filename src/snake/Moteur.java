/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snake;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JFrame;

public class Moteur {

	private Champ champDeJeu;
	private Direction directionSerpent;
	private Direction directionSerpentMemoire;
	private Direction directionSerpent2;
	private Direction directionSerpentMemoire2;
	private Random rand = new Random();
	private boolean startPressed = false;
	private Niveau niveau;
	private int nbJoueurs;
	private int score1;
	private int score2;
	private Transition transition;
	private Coordonnees coordsMenu = new Coordonnees(0, 1);

	public Moteur(int m, Niveau n) {
		nbJoueurs = m;
		niveau = n;
		champDeJeu = new Champ();
		champDeJeu.setMoteur(this);
		initialisation();
	}

	public void initialisation() {
		if (nbJoueurs == 1) {
			ArrayList<Coordonnees> newCoordsSerpent = new ArrayList<Coordonnees>();
			for (int i = 0; i <= 3; i++) {
				newCoordsSerpent.add(new Coordonnees(i + 1, 1));
			}
			champDeJeu.setCoordsSerpent(newCoordsSerpent);
			champDeJeu.setCoordsSerpent2(new ArrayList<Coordonnees>());
			champDeJeu.setCoordsMurs(niveau.getCoordsMurs());
			champDeJeu.setCoordsFruit(new Coordonnees(30, 30));
			directionSerpent = Direction.EST;
			directionSerpentMemoire = Direction.EST;
			setScore1(0);
		} else {
			ArrayList<Coordonnees> newCoordsSerpent = new ArrayList<Coordonnees>();
			ArrayList<Coordonnees> newCoordsSerpent2 = new ArrayList<Coordonnees>();
			for (int i = 0; i <= 3; i++) {
				newCoordsSerpent.add(new Coordonnees(i + 1, 1));
				newCoordsSerpent2.add(new Coordonnees(i + 1, 3));
			}
			champDeJeu.setCoordsSerpent(newCoordsSerpent);
			champDeJeu.setCoordsSerpent2(newCoordsSerpent2);
			champDeJeu.setCoordsMurs(niveau.getCoordsMurs());
			champDeJeu.setCoordsFruit(new Coordonnees(30, 30));
			directionSerpent = Direction.EST;
			directionSerpentMemoire = Direction.EST;
			directionSerpent2 = Direction.EST;
			directionSerpentMemoire2 = Direction.EST;
			setScore1(0);
			setScore2(0);
		}
	}

	public static void main(String[] args) {

		Moteur moteur = new Moteur(1, Niveau.NiveauClassique60);
		JFrame fenetre = new JFrame();

		fenetre.addKeyListener(new Joueur(moteur));
		fenetre.setTitle("Snake_v1");
		fenetre.setLayout(new BorderLayout());
		fenetre.add(moteur.champDeJeu, BorderLayout.CENTER);
		fenetre.pack();

		fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		fenetre.setLocationRelativeTo(null);
		fenetre.setVisible(true);
		fenetre.setResizable(true);

		moteur.go();

		fenetre.setVisible(false);
		fenetre.dispose();

	}

	public int modulo(int a, int b) {
		if (a >= 0)
			return a % b;
		else
			return (a % b) + b;
	}

	private void deplacerSerpent() {

		ArrayList<Coordonnees> coordsSerpent = champDeJeu.getCoordsSerpent();
		Coordonnees coordsTete = (Coordonnees) coordsSerpent.get(coordsSerpent.size() - 1);
		Coordonnees newCoordsTete = new Coordonnees(coordsTete.getPosX(), coordsTete.getPosY());

		if (serpentSuicide(1))
			directionSerpent = directionSerpentMemoire;

		switch (directionSerpent) {
		case EST:
			newCoordsTete.setPosX(modulo(newCoordsTete.getPosX() + 1, champDeJeu.nbreDivisionsX));
			directionSerpentMemoire = Direction.EST;
			break;
		case OUEST:
			newCoordsTete.setPosX(modulo(newCoordsTete.getPosX() - 1, champDeJeu.nbreDivisionsX));
			directionSerpentMemoire = Direction.OUEST;
			break;
		case SUD:
			newCoordsTete.setPosY(modulo(newCoordsTete.getPosY() + 1, champDeJeu.nbreDivisionsY));
			directionSerpentMemoire = Direction.SUD;
			break;
		case NORD:
			newCoordsTete.setPosY(modulo(newCoordsTete.getPosY() - 1, champDeJeu.nbreDivisionsY));
			directionSerpentMemoire = Direction.NORD;
			break;
		}

		coordsSerpent.add(newCoordsTete);
		if (!serpentMange(1)) {
			coordsSerpent.remove(0);
		} else {
			creerFruit();
			score1 = score1 + 1;
		}

		if (nbJoueurs == 2) {

			ArrayList<Coordonnees> coordsSerpent2 = champDeJeu.getCoordsSerpent2();
			Coordonnees coordsTete2 = (Coordonnees) coordsSerpent2.get(coordsSerpent2.size() - 1);
			Coordonnees newCoordsTete2 = new Coordonnees(coordsTete2.getPosX(), coordsTete2.getPosY());

			if (serpentSuicide(2))
				directionSerpent2 = directionSerpentMemoire2;

			switch (directionSerpent2) {
			case EST:
				newCoordsTete2.setPosX(modulo(newCoordsTete2.getPosX() + 1, champDeJeu.nbreDivisionsX));
				directionSerpentMemoire2 = Direction.EST;
				break;
			case OUEST:
				newCoordsTete2.setPosX(modulo(newCoordsTete2.getPosX() - 1, champDeJeu.nbreDivisionsX));
				directionSerpentMemoire2 = Direction.OUEST;
				break;
			case SUD:
				newCoordsTete2.setPosY(modulo(newCoordsTete2.getPosY() + 1, champDeJeu.nbreDivisionsY));
				directionSerpentMemoire2 = Direction.SUD;
				break;
			case NORD:
				newCoordsTete2.setPosY(modulo(newCoordsTete2.getPosY() - 1, champDeJeu.nbreDivisionsY));
				directionSerpentMemoire2 = Direction.NORD;
				break;
			}

			coordsSerpent2.add(newCoordsTete2);
			if (!serpentMange(2)) {
				coordsSerpent2.remove(0);
			} else {
				creerFruit();
				score2 = score2 + 1;
			}

		}

	}

	private void creerFruit() {

		int posNewFruitX = 0, posNewFruitY = 0;

		ArrayList<Coordonnees> coordsSerpent = champDeJeu.getCoordsSerpent();
		ArrayList<Coordonnees> coordsSerpent2 = champDeJeu.getCoordsSerpent2();
		ArrayList<Coordonnees> coordsMurs = champDeJeu.getCoordsMurs();

		boolean contact = false;

		Coordonnees coordsDeTest = new Coordonnees();

		do {
			posNewFruitX = rand.nextInt(champDeJeu.nbreDivisionsX);
			posNewFruitY = rand.nextInt(champDeJeu.nbreDivisionsY);

			for (Object coords : coordsSerpent) {
				coordsDeTest = (Coordonnees) coords;
				int x = coordsDeTest.getPosX(), y = coordsDeTest.getPosY();
				if (posNewFruitX == x && posNewFruitY == y)
					contact = true;
			}

			for (Object coords : coordsSerpent2) {
				coordsDeTest = (Coordonnees) coords;
				int x = coordsDeTest.getPosX(), y = coordsDeTest.getPosY();
				if (posNewFruitX == x && posNewFruitY == y)
					contact = true;
			}

			for (Object coords : coordsMurs) {
				coordsDeTest = (Coordonnees) coords;
				int x = coordsDeTest.getPosX(), y = coordsDeTest.getPosY();
				if (posNewFruitX == x && posNewFruitY == y)
					contact = true;
			}

		} while (contact);

		champDeJeu.setCoordsFruit(new Coordonnees(posNewFruitX, posNewFruitY));

	}

	private boolean serpentMange(int serpent) {

		ArrayList<Coordonnees> coordsSerpent = new ArrayList<Coordonnees>();

		if (serpent == 1)
			coordsSerpent = champDeJeu.getCoordsSerpent();
		else
			coordsSerpent = champDeJeu.getCoordsSerpent2();

		Coordonnees coordsTete = (Coordonnees) coordsSerpent.get(coordsSerpent.size() - 1);

		boolean memeX = (coordsTete.getPosX() == champDeJeu.getCoordsFruit().getPosX());
		boolean memeY = (coordsTete.getPosY() == champDeJeu.getCoordsFruit().getPosY());

		return (memeX && memeY);

	}

	private boolean serpentMort(int serpent) {

		ArrayList<Coordonnees> coordsSerpent = new ArrayList<Coordonnees>();
		ArrayList<Coordonnees> coordsSerpentAutre = new ArrayList<Coordonnees>();

		boolean memeX = false;
		boolean memeY = false;

		if (serpent == 1) {
			coordsSerpent = champDeJeu.getCoordsSerpent();
			coordsSerpentAutre = champDeJeu.getCoordsSerpent2();
		} else {
			coordsSerpent = champDeJeu.getCoordsSerpent2();
			coordsSerpentAutre = champDeJeu.getCoordsSerpent();
		}

		ArrayList<Coordonnees> coordsMurs = champDeJeu.getCoordsMurs();
		Coordonnees coordsTete = (Coordonnees) coordsSerpent.get(coordsSerpent.size() - 1);

		boolean mort = false;

		for (int i = 0; i < (coordsSerpent.size() - 1); i++) {
			memeX = (((Coordonnees) coordsSerpent.get(i)).getPosX() == coordsTete.getPosX());
			memeY = (((Coordonnees) coordsSerpent.get(i)).getPosY() == coordsTete.getPosY());
			if (memeX && memeY)
				mort = true;
		}

		for (int i = 0; i < coordsSerpentAutre.size(); i++) {
			memeX = (((Coordonnees) coordsSerpentAutre.get(i)).getPosX() == coordsTete.getPosX());
			memeY = (((Coordonnees) coordsSerpentAutre.get(i)).getPosY() == coordsTete.getPosY());
			if (memeX && memeY)
				mort = true;
		}

		for (int j = 0; j < coordsMurs.size(); j++) {
			memeX = (((Coordonnees) coordsMurs.get(j)).getPosX() == coordsTete.getPosX());
			memeY = (((Coordonnees) coordsMurs.get(j)).getPosY() == coordsTete.getPosY());
			if (memeX && memeY)
				mort = true;
		}

		return mort;

	}

	private boolean serpentSuicide(int serpent) {

		ArrayList<Coordonnees> coordsSerpent = new ArrayList<Coordonnees>();
		Direction direction = Direction.EST;

		if (serpent == 1) {
			coordsSerpent = champDeJeu.getCoordsSerpent();
			direction = directionSerpent;
		} else {
			coordsSerpent = champDeJeu.getCoordsSerpent2();
			direction = directionSerpent2;
		}

		Coordonnees coordsTete = (Coordonnees) coordsSerpent.get(coordsSerpent.size() - 1);
		Coordonnees coordsAvantTete = (Coordonnees) coordsSerpent.get(coordsSerpent.size() - 2);
		Coordonnees newCoordsTete = new Coordonnees(coordsTete.getPosX(), coordsTete.getPosY());

		switch (direction) {
		case EST:
			newCoordsTete.setPosX(modulo(newCoordsTete.getPosX() + 1, champDeJeu.nbreDivisionsX));
			break;
		case OUEST:
			newCoordsTete.setPosX(modulo(newCoordsTete.getPosX() - 1, champDeJeu.nbreDivisionsX));
			break;
		case SUD:
			newCoordsTete.setPosY(modulo(newCoordsTete.getPosY() + 1, champDeJeu.nbreDivisionsY));
			break;
		case NORD:
			newCoordsTete.setPosY(modulo(newCoordsTete.getPosY() - 1, champDeJeu.nbreDivisionsY));
			break;
		}

		boolean memeX = coordsAvantTete.getPosX() == newCoordsTete.getPosX();
		boolean memeY = coordsAvantTete.getPosY() == newCoordsTete.getPosY();

		return (memeX && memeY);

	}

	private void play() {
		transition = Transition.JEU;
		initialisation();
		while (!serpentMort(1) && !serpentMort(nbJoueurs) && score1 < 1) {
			if (!startPressed) {
				deplacerSerpent();
			}
			champDeJeu.repaint();
			try {
				Thread.sleep(niveau.getVitesse());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private void jeuClassique() {
		niveau = Niveau.NiveauClassique60;
		play();
		gameover();
	}

	private void jeu() {
		if (nbJoueurs == 1) {
			niveau = Niveau.Niveau1;
			play();
			if (!serpentMort(1)) {
				finNiveau();
				niveau = Niveau.Niveau2;
				play();
				if (!serpentMort(1)) {
					finNiveau();
					niveau = Niveau.Niveau3;
					play();
					if (!serpentMort(1)) {
						finNiveau();
						niveau = Niveau.Niveau4;
						play();
						if (!serpentMort(1)) {
							finNiveau();
							niveau = Niveau.Niveau5;
							play();
						}
					}
				}
			}
		} else {
			niveau = Niveau.NiveauClassique60;
			play();
		}
		gameover();
	}

	private void menu() {
		transition = Transition.MENU;
		startPressed = false;
		while (!startPressed) {
			champDeJeu.repaint();
		}
		startPressed = false;
		if (coordsMenu.getPosX() == 0)
			nbJoueurs = 1;
		else
			nbJoueurs = 2;
	}

	private void finNiveau() {
		transition = Transition.FINNIVEAU;
		startPressed = false;
		while (!startPressed) {
			champDeJeu.repaint();
		}
		startPressed = false;
	}

	private void gameover() {
		transition = Transition.GAMEOVER;
		startPressed = false;
		while (!startPressed) {
			champDeJeu.repaint();
		}
		startPressed = false;
	}

	private void regles() {
		transition = Transition.REGLES;
		startPressed = false;
		while (!startPressed) {
			champDeJeu.repaint();
		}
		startPressed = false;
	}

	private void go() {
		while (true) {
			menu();
			int x = coordsMenu.getPosX();
			int y = coordsMenu.getPosY();
			if (y == 1) {
				jeu();
			} else {
				if (x == 0)
					jeuClassique();
				else
					regles();
			}
		}
	}

	public Direction getDirectionSerpent() {
		return directionSerpent;
	}

	public void setDirectionSerpent(Direction d) {
		directionSerpent = d;
	}

	public boolean isStartPressed() {
		return startPressed;
	}

	public void setStartPressed(boolean paused) {
		this.startPressed = paused;
	}

	public Direction getDirectionSerpent2() {
		return directionSerpent2;
	}

	public void setDirectionSerpent2(Direction directionSerpent2) {
		this.directionSerpent2 = directionSerpent2;
	}

	public Direction getDirectionSerpentMemoire2() {
		return directionSerpentMemoire2;
	}

	public void setDirectionSerpentMemoire2(Direction directionSerpentMemoire2) {
		this.directionSerpentMemoire2 = directionSerpentMemoire2;
	}

	public int getScore1() {
		return score1;
	}

	public void setScore1(int score1) {
		this.score1 = score1;
	}

	public int getScore2() {
		return score2;
	}

	public void setScore2(int score2) {
		this.score2 = score2;
	}

	public int getNbJoueurs() {
		return nbJoueurs;
	}

	public void setNbJoueurs(int nbJoueurs) {
		this.nbJoueurs = nbJoueurs;
	}

	public Transition getTransition() {
		return transition;
	}

	public void setTransition(Transition transition) {
		this.transition = transition;
	}

	public Coordonnees getCoordsMenu() {
		return coordsMenu;
	}

	public void setCoordsMenu(Coordonnees coordsMenu) {
		this.coordsMenu = coordsMenu;
	}

}
