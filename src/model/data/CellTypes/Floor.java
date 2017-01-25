package model.data.CellTypes;

/**
 * 
 * Class Contains Cell type floor data
 *
 * 
 */

public class Floor extends CellType {
	public Floor() {
		super();
		this.setWalkeable(new canWalk());

	}

	@Override
	public CellType getType() {
		return this;
	}
}
