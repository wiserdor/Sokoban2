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

	/**
	 * default constructor, interpreters the move commands into keys (points)
	 * 
	 * @param l
	 *            level holder
	 * @param Arrow
	 *            direction as received by the user
	 */
	public MySokobanPolicy(Level l) {
		this.l = l;
		this.p = new Point(l.getCharacterPosition());
		this.pNext = new Point();
		
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
	public void move() {
		if (l.getBoardObjects().get(p).tryToWalk()) {			 // if you can walk on it just walk
			l.setCharacterPosition(p);
			System.out.println("Moving...");
		} else {
			if (l.getBoardObjects().get(p).tryToMove()) { 		// if you can move it							
				if (l.getBoardObjects().get(pNext).tryToWalk()) {
					l.move(p, pNext); 							// check next point in that direction is walkable
													
					System.out.println("Pushing...");
				}
				else System.out.println("Can't Move");
			} else {
				System.out.println("Can't Move");
			}
		}
		l.refreshBoard();
		if (l.isFinished())
			System.out.println("=============Congratulations!!! You Win!==============");
	}
}
