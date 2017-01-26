package view;

import java.awt.Point;
import java.io.IOException;

import model.data.CellTypes.CellType;

public interface View {

	public void start();
	public void displayError(String msg);
	public void display(Character[][] board);

}
