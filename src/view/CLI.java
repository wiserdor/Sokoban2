package view;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Observable;
import java.util.Scanner;

import common.Position;
import controller.commands.Commands;

/**
 * Class Responsible to create interaction between the User input and the Command Classes
 * 
 */


public class CLI extends Observable implements View {


		@Override
		public void start() {
			Scanner scanner = new Scanner(System.in);
			Thread thread = new Thread(new Runnable() {
				
				@Override
				public void run() {
					while (true) {
						System.out.println("Please enter a choice");
						String cmdChoice = sc.nextLine();
						String[] cmdSplit = cmdChoice.split(" ", 2);
						setChanged();
						notifyObservers(cmdSplit);
						
						if (commandLine.equals("exit"))
							break;
					}				
				}
			});
			thread.start();		
		}

		@Override
		public void displayError(String msg) {
			System.out.println("Error: " + msg);
			
		}

	}

 	

}
