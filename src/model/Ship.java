/**
 * BINOME : 
 * Dupuy-roudel Hugo : 21306151
 * Lebert Jasmine : 21301704
 */
package model;
/**
 * Classe representant un vaisseau/moto
 */
public class Ship extends Element {

	Direction a_direction;
	/**
	 * Constructeur d'un vaisseau
	 * @param i_board
	 * @param i_posX
	 * @param i_posY
	 * @param i_direction
	 */
	public Ship(GameBoard i_board, int i_posX, int i_posY, Direction i_direction) {
		super(i_board, i_posX, i_posY);
		a_direction = i_direction;
	}
	/**
	 * renvoit la direction du vaisseau
	 * @return
	 */
	public String get_direction(){
		return a_direction.toString();
	}
	
	public Direction get_vrai_direction() {
		return a_direction;
	}
	/**
	 * Bouge le vaisseau selon la direction
	 * @param i_direction
	 * @return renvoit vrai si le vaisseau à pu bouger, faux sinon (il est alors crashé)
	 */
	public boolean move(Direction i_direction){
		//pour l'instant, ne fait rien si le robot ne peux pas avancer, exception plutôt?
		// NON, exception pour exceptionnel
		//a_direction.move_straight();
		//ne prend pas en compte l'impossibilité de reculer
		int posX = a_posX, posY = a_posY;
		
		// modification de la position
		if(i_direction == Direction.NORTH){
			posX = a_posX-1;
		}
		else if(i_direction == Direction.SOUTH){
			posX = a_posX+1;
		}
		else if(i_direction == Direction.EAST){
			posY = a_posY+1;
		}
		else if(i_direction == Direction.WEST){
			posY = a_posY-1;
		}
		//Le vaisseau ne peux pas se deplacer, soit il y a deja un objet, soit il touche le bord du plateau,
		//Le vaisseau a donc perdu
		if (!a_board.move(this, posX, posY, i_direction)){
//			System.out.println("\n Crash, le vaisseau a perdu \n");
			// à éviter pour que ça ne s'affiche pas pour les copies
			return false;
		}
		return true;
	}
	

	/**
	 * 
	 * @param i_d
	 */
	public void set_direction(Direction i_d) {
		a_direction = i_d;
	}
	
	@Override
	public Ship copy(GameBoard i_board){
		return new Ship(i_board, a_posX, a_posY, a_direction);
	}
	
	@Override
	public int get_int_value() {
		return -2;
	}
	
	/**
	 * teste si deux vaisseaux sont égaux
	 * @param ship
	 * @return
	 */
	@Override
	public boolean is_equal(Element ship) {
		Ship tmp_ship = (Ship) ship;
		boolean samePosition = super.is_equal(ship);
		boolean sameDirection = tmp_ship.get_direction() == get_direction();
		return samePosition && sameDirection;
	}


}
