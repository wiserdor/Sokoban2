package model.CellTypes;

/**
 * 
 * Super Class Defines and Contains celltypes data
 *
 */
public class CellType {

	private Moveable moveable;
	private Walkable walkable;
	private char charValue;

	/**
	 * Default Constructor
	 */
	public CellType() {
		this.setMovingAbility(new CanMove());
		this.setWalkeable(new cantWalk());
	}

	/**
	 * Checks if Certein Celltype is moveable
	 * 
	 * @return true if Celltype is moveable
	 * @see Moveable
	 */
	public boolean tryToMove() {
		return moveable.isMoveable();
	}

	/**
	 * Checks if Certein Celltype is walkable
	 * 
	 * @return true if Celltype is walkable
	 * @see Walkable
	 */
	public boolean tryToWalk() {
		return walkable.isWalkable();
	}

	public void setMovingAbility(Moveable newMoveable) {
		moveable = newMoveable;

	}

	public void setCharValue(char charValue) {
		this.charValue = charValue;
	}

	public char getCharValue1() {
		// TODO Auto-generated method stub
		return this.charValue;
	}

	public void setWalkeable(Walkable walkable) {
		this.walkable = walkable;
	}

	public CellType getType() {
		// TODO Auto-generated method stub
		return this;
	}

}
