package IO;
/**
 * this class contains the varies creators of the
 *  level saver and creating savers by demand
 */

import java.util.HashMap;

public class LevelSaveCreators {

	private static HashMap<String, Creator> extensions;
	private String filetype;

	private interface Creator {
		public LevelSaver create();
	}
	/**
	 * default constructor
	 */

	public LevelSaveCreators() {
		extensions = new HashMap<String, Creator>();
		extensions.put("txt", new TxtCreator());
		extensions.put("obj", new ObjCreator());
		extensions.put("xml", new XmlCreator());

	}
	/**
	 *  getting the file type and creating the matching creator and the matching saver
	 * @param filePath the full file path as string
	 * @return the matching saver creator
	 */

	public LevelSaver Createsaver(String filetype) {
		this.filetype = filetype.substring(filetype.lastIndexOf('.')+1,filetype.length());
		
		Creator c = extensions.get(this.filetype);

		if (c != null)
			return c.create();
		return null;
	}
	/**
	 * Creating new Object file type saver
	 * @see MyObjectLevelSaver
	 *
	 */
	private class TxtCreator implements Creator {
		public LevelSaver create() {
			return new MyTextLevelSaver();
		}
	}
	/**
	 * Creating new text file type saver
	 * @see MyTextLevelSaver
	 *
	 */
	private class ObjCreator implements Creator {
		public LevelSaver create() {
			return new MyObjectLevelSaver();
		}
	}
	/**
	 * Creating new XML file type saver
	 * @see MyXmlLevelSaver
	 *
	 */
	private class XmlCreator implements Creator {
		public LevelSaver create() {
			return new MyXmlLevelSaver();
		}
	}
}
