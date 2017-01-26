package view;

import java.awt.Point;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Observable;

import model.data.CellTypes.CellType;
import model.data.IO.CellValueCreator;

public class MyView extends Observable implements View  {

	@Override
	public void start() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void display(CellType[][] arr,Point CharacterPosition ) throws IOException {
			
	}

	@Override
	public void displayError(String msg) {
		// TODO Auto-generated method stub
		
	}

}
