package CellTypes;
/**
 * this class contains the varies creators of the
 *  CellType and creating CellType by demand
 */
import java.util.HashMap;

import IO.LevelLoader;
import IO.MyObjectLevelLoader;
import IO.MyTextLevelLoader;
import IO.MyXmlLevelLoader;

public class CellTypeCreators {

	private HashMap<String, Creator> MapCreator;
	private String CellString;

	private interface Creator {
		public CellType create();
	}
/**
 * default constructor 
 */
	public CellTypeCreators() {
		MapCreator = new HashMap<String, Creator>();
		MapCreator.put("Box", new BoxCreator());
		MapCreator.put("Goal", new GoalCreator());
		MapCreator.put("GoalBox", new GoalBoxCreator());
		MapCreator.put("Hero", new HeroCreator());
		MapCreator.put("Wall", new WallCreator());

	}
	/**
	 *  getting the cell type and creating the matching creator and the matching CellType
	 * @param CellType type as string
	 * @return the matching CellType Creator
	 */
	public CellType CreateCell(String CellString) {
		this.CellString = CellString;
		Creator c = MapCreator.get(CellString);
		if (c != null)
			return c.create();
		return null;
	}

	private class BoxCreator implements Creator {
		public CellType create() {
			return new Box();
		}
	}

	private class GoalCreator implements Creator {
		public CellType create() {
			return new Goal();
		}
	}

	private class GoalBoxCreator implements Creator {
		public CellType create() {
			return new GoalBox();
		}
	}

	private class HeroCreator implements Creator {
		public CellType create() {
			return new Hero();
		}
	}

	private class WallCreator implements Creator {
		public CellType create() {
			return new Wall();
		}
	}

}
