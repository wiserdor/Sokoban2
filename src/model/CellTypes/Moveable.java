package model.CellTypes;

/**
 * this interface determine if the cell type that implements it can be moved on
 * the board
 *
 */

public interface Moveable {

	boolean isMoveable();

}

/**
 * the class determined that the cell type can be moved
 *
 */

class CanMove implements Moveable {

	@Override
	public boolean isMoveable() {

		return true;
	}

}

/**
 * * the class determined that the cell type can't be moved
 * 
 */

class CantMove implements Moveable {

	@Override
	public boolean isMoveable() {

		return false;
	}

}