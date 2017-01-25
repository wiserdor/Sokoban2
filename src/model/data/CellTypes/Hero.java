package model.data.CellTypes;

/**
 * Class Contains Cell type hero data
 *
 */

public class Hero extends CellType {

	static Hero instance = null;

	public Hero() {
		super();

	}

	public Hero getInstance() {
		if (Hero.instance == null) {
			Hero.instance = new Hero();
		}
		return instance;
	}
}