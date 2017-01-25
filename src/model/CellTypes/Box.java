package CellTypes;

/**
 * 
 * Class Contains Cell type Box data
 *
 */
public class Box extends CellType {
	private char charType;

	/**
	 * Default constructor
	 * sets box moving ability
	 */
	public Box() {
		super();
		this.setMovingAbility(new CanMove());
		this.setWalkeable(new cantWalk());

	}

	public char getCharType() {
		return charType;
	}

	public void setCharType(char charType) {
		this.charType = charType;
	}

	@Override
	public CellType getType() {
		// TODO Auto-generated method stub
		return this;
	}

}
