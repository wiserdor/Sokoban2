package model;

import java.util.Observable;

import model.policy.MySokobanPolicy;

public class MyModel extends Observable implements Model {
	private MySokobanPolicy policy;
	@Override
	public void move(String Arrow) {
		switch (Arrow) {
		case "Right":
		case "right":
			p.setLocation(p.getX() + 1, p.getY());
			;
			pNext.setLocation(p.getX() + 1, p.getY());
			break;
		case "Left":
		case "left":
			p.setLocation(p.getX() - 1, p.getY());
			pNext.setLocation(p.getX() - 1, p.getY());
			break;
		case "Up":
		case "up":
			p.setLocation(p.getX(), p.getY() - 1);
			pNext.setLocation(p.getX(), p.getY() - 1);
			break;
		case "down":
		case "Down":
			p.setLocation(p.getX(), p.getY() + 1);
			pNext.setLocation(p.getX(), p.getY() + 1);
			break;

		default:
			break;
		}
	}
	
	

}
