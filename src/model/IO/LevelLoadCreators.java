package model.IO;
/**
 * this class contains the varies creators of the
 *  level loader and creating loaders by demand
 */

import java.util.HashMap;

public class LevelLoadCreators {
	private static HashMap<String, Creator> extensions;
	private String filePath;

	private interface Creator {
		public LevelLoader create();
	}
	/**
	 * default constructor
	 */

	public LevelLoadCreators() {
		extensions = new HashMap<String, Creator>();
		extensions.put("txt", new TxtCreator());
		extensions.put("obj", new ObjCreator());
		extensions.put("xml", new XmlCreator());

	}
	/**
	 *  getting the file type and creating the matching creator and the matching loader
	 * @param filePath the full file path as string
	 * @return the matching loader creator
	 */

	public LevelLoader CreateLoader(String filePath) {
		this.filePath = filePath;
		if (filePath.lastIndexOf('.') >= 0) {
			Creator c = extensions.get(filePath.substring(filePath.lastIndexOf('.') + 1, filePath.length()));
			if (c != null)
				return c.create();
		}
		return null;
	}
	
/**
 * Creating new text file type loader
 * @see MyTextLevelLoader
 *
 */

	private class TxtCreator implements Creator {
		public LevelLoader create() {
			return new MyTextLevelLoader();
		}
	}
	/**
	 * Creating new object file type loader
	 * @see MyObjectLevelLoader
	 *
	 */
	private class ObjCreator implements Creator {
		public LevelLoader create() {
			return new MyObjectLevelLoader();
		}
	}
	/**
	 * Creating new XML file type loader
	 * @see MyTextLevelLoader
	 *
	 */
	private class XmlCreator implements Creator {
		public LevelLoader create() {
			return new MyXmlLevelLoader();
		}
	}
}
