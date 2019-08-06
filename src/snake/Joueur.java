/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snake;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Joueur implements KeyListener {

	private Moteur moteur;

	public Joueur(Moteur m) {
		moteur = m;
	}

	public void keyPressed(KeyEvent e) {

		if (moteur.getTransition() == Transition.JEU) {
			switch (e.getKeyCode()) {
			case (37):
				moteur.setDirectionSerpent(Direction.OUEST);
				break;
			case (38):
				moteur.setDirectionSerpent(Direction.NORD);
				break;
			case (39):
				moteur.setDirectionSerpent(Direction.EST);
				break;
			case (40):
				moteur.setDirectionSerpent(Direction.SUD);
				break;

			case (81):
				moteur.setDirectionSerpent2(Direction.OUEST);
				break;
			case (90):
				moteur.setDirectionSerpent2(Direction.NORD);
				break;
			case (68):
				moteur.setDirectionSerpent2(Direction.EST);
				break;
			case (83):
				moteur.setDirectionSerpent2(Direction.SUD);
				break;

			case (32):
				moteur.setStartPressed(!(moteur.isStartPressed()));
				break;
			}
		}

		if (moteur.getTransition() == Transition.MENU) {
			switch (e.getKeyCode()) {
			case (37):
				//moteur.setDirectionSerpent(Direction.OUEST);
				moteur.getCoordsMenu().setPosX(moteur.modulo(moteur.getCoordsMenu().getPosX() - 1, 2));
				break;
			case (38):
				//moteur.setDirectionSerpent(Direction.NORD);
				moteur.getCoordsMenu().setPosY(moteur.modulo(moteur.getCoordsMenu().getPosY() + 1, 2));
				break;
			case (39):
				//moteur.setDirectionSerpent(Direction.EST);
				moteur.getCoordsMenu().setPosX(moteur.modulo(moteur.getCoordsMenu().getPosX() + 1, 2));
				break;
			case (40):
				//moteur.setDirectionSerpent(Direction.SUD);
				moteur.getCoordsMenu().setPosY(moteur.modulo(moteur.getCoordsMenu().getPosY() - 1, 2));
				break;
			
			case (32):
				moteur.setStartPressed(true);
				break;
			}
		}

		if (moteur.getTransition() == Transition.GAMEOVER) {
			switch (e.getKeyCode()) {
			case (32):
				moteur.setStartPressed(true);
				break;
			}
		}
		
		if (moteur.getTransition() == Transition.FINNIVEAU) {
			switch (e.getKeyCode()) {
			case (32):
				moteur.setStartPressed(true);
				break;
			}
		}
		
		if (moteur.getTransition() == Transition.REGLES) {
			switch (e.getKeyCode()) {
			case (32):
				moteur.setStartPressed(true);
				break;
			}
		}

	}

	public void keyReleased(KeyEvent e) {
	}

	public void keyTyped(KeyEvent e) {
	}

}

