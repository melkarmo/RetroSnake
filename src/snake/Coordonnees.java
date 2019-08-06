/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snake;

public class Coordonnees {
	
	
	private int posX;
	private int posY;
	
	
	public Coordonnees(){
		posX = 0;
		posY = 0;
	}
	
	
	public Coordonnees(int x, int y){
		posX = x;
		posY = y;
	}
	
	public int getPosX(){
		return posX;
	}

	public int getPosY(){
		return posY;
	}
	
	public void setPosX(int x){
		posX = x;
	}
	
	public void setPosY(int y){
		posY = y;
	}
	
	public boolean egal (Coordonnees coords){
		return ((coords.getPosX() == posX) && (coords.getPosY() == posY));
	}
	
}

