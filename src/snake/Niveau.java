/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snake;

import java.util.ArrayList;

public enum Niveau {
	
	NiveauClassique60(coordsMursNiveau(0),60,32*32),
	Niveau1(coordsMursNiveau(1),100,30),
	Niveau2(coordsMursNiveau(2),85,45),
	Niveau3(coordsMursNiveau(3),70,60),
	Niveau4(coordsMursNiveau(4),60,75),
	Niveau5(coordsMursNiveau(5),55,100);
	
	private ArrayList<Coordonnees> coordsMurs;
	private int vitesse;
	private int scoreMax;
	
	Niveau (ArrayList<Coordonnees> cM, int v, int s) {
	    this.setCoordsMurs(cM);
	    this.setVitesse(v);
	    this.setScoreMax(s);
	}
	
	public static ArrayList<Coordonnees> coordsMursNiveau(int lvl) {
		ArrayList<Coordonnees> newCoordsMurs = new ArrayList<Coordonnees>();
		switch (lvl) {
		case 1:
			for(int i = 0; i < 16; i++)
			{
				newCoordsMurs.add(new Coordonnees(8+i,15));
			}
			break;
		case 2:
			for(int i = 0; i < 12; i++)
			{
				newCoordsMurs.add(new Coordonnees(4+i,9));
				newCoordsMurs.add(new Coordonnees(17+i,22));
			}
			break;
		case 3:
			for(int i = 0; i < 15; i++)
			{
				newCoordsMurs.add(new Coordonnees(9+i,15));
				newCoordsMurs.add(new Coordonnees(5,8+i));
				newCoordsMurs.add(new Coordonnees(27,8+i));
			}
			break;
		case 4:
			for(int i = 0; i < 12; i++)
			{
				newCoordsMurs.add(new Coordonnees(4+i,6));
				newCoordsMurs.add(new Coordonnees(17+i,11));
				newCoordsMurs.add(new Coordonnees(4+i,16));
				newCoordsMurs.add(new Coordonnees(17+i,21));
				newCoordsMurs.add(new Coordonnees(4+i,26));
			}
			break;
		case 5:
			for(int i = 0; i < 10; i++)
			{
				newCoordsMurs.add(new Coordonnees(4+i,16));
				newCoordsMurs.add(new Coordonnees(19+i,16));
				newCoordsMurs.add(new Coordonnees(16,4+i));
				newCoordsMurs.add(new Coordonnees(16,19+i));
			}
			break;
		}
		return newCoordsMurs;
	}

	
	public ArrayList<Coordonnees> getCoordsMurs() {
		return coordsMurs;
	}

	public void setCoordsMurs(ArrayList<Coordonnees> coordsMurs) {
		this.coordsMurs = coordsMurs;
	}

	public int getVitesse() {
		return vitesse;
	}

	public void setVitesse(int vitesse) {
		this.vitesse = vitesse;
	}

	public int getScoreMax() {
		return scoreMax;
	}

	public void setScoreMax(int scoreMax) {
		this.scoreMax = scoreMax;
	}
	
}
