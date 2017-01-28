package view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Observable;

public class CLI extends Observable implements View {

	private BufferedReader reader;
	private PrintWriter writer;
	private String exitString;

	public CLI(BufferedReader reader, PrintWriter writer, String exitString) {
		super();
		this.reader = reader;
		this.writer = writer;
		this.exitString = exitString;
	}

	@Override
	public void start() {
		Thread thread = new Thread(new Runnable() {

			@Override
			public void run() {
				String cmdChoice="";
				while (true) {
					writer.println("Please enter a choice");
					writer.flush();
					do{
					try {
						cmdChoice = reader.readLine();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					String[] cmdSplit = cmdChoice.split(" ", 2);
					setChanged();
					notifyObservers(cmdSplit);

					if (cmdChoice.equals("exit"))
						break;
					} while (!cmdChoice.equals(exitString));
				}
			}
		});
		thread.start();
	}

	@Override
	public void displayError(String msg) {
		System.out.println("Error: " + msg);
		
	}


	@Override
	public void display(Character[][] board) {
		// TODO Auto-generated method stub
		/*for(int i=0;i<board.length;i++){
		for(int j=0;j<board[i].length;j++)
			writer.print(board[i][j]);
		writer.println();
	}
	writer.flush();
		*/	
	}

}
