package controller;

import java.util.Observable;

import model.Model;
import view.View;

public class MyController extends Observable  implements Controller{
	private Model model;
	private View view;
	private Controller controller;


	@Override
	public void update(Observable arg0, Object arg1) {
		
		
	}
	

}
