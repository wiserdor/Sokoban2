package model;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public interface Model {
	public void move(String Arrow);
	public void load(String path) throws FileNotFoundException, ClassNotFoundException, IOException;
	public Character[][] getDisplay() throws IOException;
	public void save(String path) throws IOException;
	public boolean isFinished();
	public int countSteps();
}
