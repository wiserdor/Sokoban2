package model.policy;

/**
 * the class determines the game rules and operating the moves according to those rules. 
 */
import java.awt.Point;

import controller.commands.LoadCommand;
import model.data.level.Level;

public class MySokobanPolicy {
	private Level l;
	private Point p;
	private Point pNext;
	private String Arrow;
	private boolean isValid;

	/**
	 * default constructor, interpreters the move commands into keys (points)
	 * 
	 * @param l
	 *            level holder
	 * @param Arrow
	 *            direction as received by the user
	 */
	public MySokobanPolicy(Level l) {
		this.isValid=true;
		this.l = l;
		p=new Point();
		p.setLocation(l.getCharacterPosition().getX(),l.getCharacterPosition().getY());
		this.pNext = new Point();

	}

	public boolean isValid() {
		return isValid;
	}

	public void setValid(boolean isValid) {
		this.isValid = isValid;
	}

	public Level getL() {
		return l;
	}

	public void setL(Level l) {
		this.l = l;
	}

	public Point getP() {
		return p;
	}

	public void setP(Point p) {
		this.p = p;
	}

	public Point getpNext() {
		return pNext;
	}

	public void setpNext(Point pNext) {
		this.pNext = pNext;
	}

	public String getArrow() {
		return Arrow;
	}

	public void setArrow(String arrow) {
		Arrow = arrow;
	}

	/**
	 * Verifying the policy rules and executing the command
	 */
	public void move(String Arrow) {
		this.isValid=true;
		p=new Point();
		p.setLocation(l.getCharacterPosition().getX(),l.getCharacterPosition().getY());
		this.pNext = new Point();
		switch (Arrow) {
		case "Right":
		case "right":
			p.setLocation(p.getX() + 1, p.getY());
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
			System.out.println("not a valid direction");
			isValid = false;
			break;
		}
		if (isValid) {
			if (l.getBoardObjects().get(p).tryToWalk()) { // if you can walk on
															// it just walk
				l.setCharacterPosition(p);
				System.out.println("Moving...");
			} else {
				if (l.getBoardObjects().get(p).tryToMove()) { // if you can move
																// it
					if (l.getBoardObjects().get(pNext).tryToWalk()) {
						l.move(p, pNext); // check next point in that direction
											// is walkable

						System.out.println("Pushing...");
					} else{
						System.out.println("Can't Move");
						isValid = false;
					}
				} else {
					System.out.println("Can't Move");
					isValid = false;

				}
			}
			l.refreshBoard();
			if (l.isFinished())
				System.out.println("=============Congratulations!!! You Win!==============");
		}
		
	}
}
