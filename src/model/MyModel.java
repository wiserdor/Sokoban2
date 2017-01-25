package model;

import java.util.Observable;

import model.data.level.Level;
import model.policy.MySokobanPolicy;

public class MyModel extends Observable implements Model {
	private MySokobanPolicy policy;
	private Level l;
	@Override
	public void move(String Arrow) {
		switch (Arrow) {
		case "Right":
		case "right":
			policy.getP().setLocation(policy.getP().getX() + 1,policy.getP().getY());
			policy.getpNext().setLocation(policy.getP().getX() + 1,policy.getP().getY());
			break;
		case "Left":
		case "left":
			policy.getP().setLocation(policy.getP().getX() - 1, policy.getP().getY());
			policy.getpNext().setLocation(policy.getP().getX() - 1, policy.getP().getY());
			break;
		case "Up":
		case "up":
			policy.getP().setLocation(policy.getP().getX(), policy.getP().getY() - 1);
			policy.getpNext().setLocation(policy.getP().getX(), policy.getP().getY() - 1);
			break;
		case "down":
		case "Down":
			policy.getP().setLocation(policy.getP().getX(), policy.getP().getY() + 1);
			policy.getpNext().setLocation(policy.getP().getX(), policy.getP().getY() + 1);
			break;

		default:
			System.out.println("not a valid direction");
			return;
		}
		this.setChanged();
	}
	public MyModel(MySokobanPolicy policy) {
		super();
		this.policy = policy;
	}
	public MyModel() {
		super();
		this.policy = new MySokobanPolicy(l);
	}
	@Override
	public void load(String path) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void display(String path) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void save(String path) {
		// TODO Auto-generated method stub
		
	}
	
	

}
