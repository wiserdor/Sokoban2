package model.policy;

/**
 * the class determines the game rules and operating the moves according to those rules. 
 */
import java.awt.Point;

import model.Commands.LoadCommand;

public class MySokobanPolicy {
	private LoadCommand l;
	private Point p;
	private Point pNext;
	private String Arrow;

	/**
	 * default constructor, interpreters the move commands into keys (points)
	 * 
	 * @param l
	 *            level holder
	 * @param Arrow
	 *            direction as received by the user
	 */
	public MySokobanPolicy(LoadCommand l, String Arrow) {
		this.l = l;
		this.Arrow = Arrow;
		this.p = new Point(l.getLevel().getCharacterPosition());
		this.pNext = new Point();
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

	/**
	 * Verifying the policy rules and executing the command
	 */
	public void move() {
		if (l.getLevel().getBoardObjects().get(p).tryToWalk()) {			 // if you can walk on it just walk
			l.getLevel().setCharacterPosition(p);
			System.out.println("Moving...");
		} else {
			if (l.getLevel().getBoardObjects().get(p).tryToMove()) { 		// if you can move it							
				if (l.getLevel().getBoardObjects().get(pNext).tryToWalk()) {
					l.getLevel().move(p, pNext); 							// check next point in that direction is walkable
													
					System.out.println("Pushing...");
				}
				else System.out.println("Can't Move");
			} else {
				System.out.println("Can't Move");
			}
		}
		l.getLevel().refreshBoard();
		if (l.getLevel().isFinished())
			System.out.println("=============Congratulations!!! You Win!==============");
	}
}
