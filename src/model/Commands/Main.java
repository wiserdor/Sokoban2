package Commands;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {

	public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
		System.out.println("	===================SOKOBAN=======================");
		System.out.println("Load <Full Path>			: Load level from file");
		System.out.println("Display       			   	: Display level Map");
		System.out.println("Move <Up><Down><Left><Right>		: Move character to desired direction");
		System.out.println("Save <Full Path>			: Save level from file");

		CLI cli = new CLI();

	}

}
