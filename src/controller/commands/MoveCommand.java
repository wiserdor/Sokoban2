package controller.commands;

import model.policy.MySokobanPolicy;

public class MoveCommand implements Commands {
	private LoadCommand levelHolder;
	private String Arrow;

	/**
	 * Constructor must get levelHolder and Arrow
	 * 
	 * @param levelHolder
	 *            the level object
	 * @param Arrow
	 *            the direction input by the user
	 */
	public MoveCommand(LoadCommand levelHolder, String Arrow) {
		this.levelHolder = levelHolder;
		this.Arrow = Arrow;
	}

	/**
	 * creates the move according to the policy of the current session
	 * @see MySokobanPolicy
	 */
	public void execute() {

		MySokobanPolicy m = new MySokobanPolicy(levelHolder, Arrow);
		m.move();

	}

}
