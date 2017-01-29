package view;

import java.io.FileNotFoundException;

public interface View {

	public void start();
	public void display(Character[][] board);
	public void setWin() throws FileNotFoundException;

}
