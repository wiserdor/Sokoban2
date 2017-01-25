package CellTypes;
/**
 * 
 * Class Contains Cell type goalbox data
 *
 */

public class GoalBox extends CellType {
	public GoalBox() {
		super();
		this.setMovingAbility(new CanMove());
	}

}
