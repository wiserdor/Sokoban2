package Commands;

/**
 * Class Responsible to create interaction between the User input and the Command Classes
 * 
 */
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class CLI {
	private LoadCommand LevelHolder;

	/**
	 * Default Constructor 
	 * @throws FileNotFoundException
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public CLI() throws FileNotFoundException, ClassNotFoundException, IOException {
		Scanner sc = new Scanner(System.in);
		LoadCommand LevelHolder = null;
		Commands cmd = null;
		while (!(cmd instanceof ExitCommand)) {
			System.out.println("Please enter a choice");
			String cmdChoice = sc.nextLine();
			String[] cmdSplit = cmdChoice.split(" ", 2);

			switch (cmdSplit[0]) {
			case "load":
			case "Load":
				LevelHolder = new LoadCommand(cmdSplit[1]);
				cmd = LevelHolder;
				break;
			case "display":
			case "Display":
				if (LevelHolder != null)
					cmd = new DisplayCommand(LevelHolder);
				break;
			case "Move":
			case "move":
				if (LevelHolder != null)
					cmd = new MoveCommand(LevelHolder, cmdSplit[1]);
				break;
			case "save":
			case "Save":
				if (LevelHolder != null)
					cmd = new SaveCommand(LevelHolder, cmdSplit[1]);
				break;
			case "exit":
			case "Exit":
				cmd = new ExitCommand();
				break;
			default:
				cmd =null;
				break;
			}
			try {
				invoker(cmd);

			} catch (Exception e) {
				System.out.println("Invalid Command");
			}
		}
	}
/**
 * Method gets a command and execute the appropriate Command Method
 * @param cmd the users command
 * @throws FileNotFoundException
 * @throws IOException
 * @throws ClassNotFoundException
 */
	public void invoker(Commands cmd) throws FileNotFoundException, IOException, ClassNotFoundException {
		cmd.execute();

	}

}
