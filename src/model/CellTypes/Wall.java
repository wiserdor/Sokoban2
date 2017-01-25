package model.CellTypes;

/**
 * 
 * Class Contains Cell type wall data
 *
 */

public class Wall extends CellType {

	public Wall() {
		super();
		this.setWalkeable(new cantWalk());
		this.setMovingAbility(new CantMove());
	}

	@Override
	public CellType getType() {
		return this;
	}

}
