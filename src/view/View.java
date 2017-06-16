package view;

import java.io.FileNotFoundException;

public interface View {

	public void start();
	public void display(Character[][] board);
	public void display(Character[][] board,int countSteps);
	public void setWin() throws FileNotFoundException;
	public void exitGui();
	public Double getFinishTime();

}
