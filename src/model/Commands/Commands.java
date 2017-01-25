package model.Commands;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface Commands {

	public void execute() throws FileNotFoundException, IOException, ClassNotFoundException;

}
