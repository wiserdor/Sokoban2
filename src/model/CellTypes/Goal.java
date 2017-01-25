package model.CellTypes;

/**
 * Class Contains Cell type goal data
 * 
 */

public class Goal extends CellType {

	@Override
	public CellType getType() {
		return this;
	}

	public Goal() {
		super();
		this.setWalkeable(new canWalk());
	}

}
