package CellTypes;
/**
 * 
 * this interface determine if the cell type that implements it can be walked on at the board
 *
 */

public interface Walkable {
	boolean isWalkable();
}

/**
 * 
 * the class determined that the cell type can be walked on
 *
 */

class canWalk implements Walkable {

	@Override
	public boolean isWalkable() {

		return true;
	}

}

/**
 * 
 * the class determined that the cell type can't be walked on
 *
 */

class cantWalk implements Walkable {

	@Override
	public boolean isWalkable() {

		return false;
	}

}