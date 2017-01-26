package controller.commands;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public abstract class Commands {

	protected String params;

	public abstract void execute() throws FileNotFoundException, IOException, ClassNotFoundException;

	public void setParams(String params) {
		this.params = params;
	}
}
