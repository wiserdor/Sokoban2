package model.data.IO;
/**
 * this class store the cell type char values
 */

import java.util.HashMap;

public class CellValueCreator {

	private static HashMap<String, Character> values;
	private String cellType;

	/**
	 * default constructor
	 */
	public CellValueCreator() {
		values = new HashMap<String, Character>();
		values.put("Floor", ' ');
		values.put("Box", '@');
		values.put("Goal", 'o');
		values.put("Wall", '#');
		values.put("GoalBox", 'v');
		values.put("Hero", 'A');
	}
	
	/**
	 * getting the cell type name and returns the char value
	 * @param cellType the cell type string value
	 * @return the cell type values name 
	 */

	public Character CreateValue(String cellType) {
		if (cellType.lastIndexOf('.') != -1) {
			this.cellType = cellType.substring(cellType.lastIndexOf('.') + 1, cellType.length());
		}
		Character c = values.get(this.cellType);
		if (c != null)
			return c;
		return ' ';
	}
}