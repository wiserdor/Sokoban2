package controller.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Observable;
import java.util.concurrent.ArrayBlockingQueue;

public class CLI extends Observable implements ClientHandler {
	private ArrayBlockingQueue<String> queue;
	private BufferedReader reader;
	private PrintWriter writer;
	private volatile boolean isConnected;
	private String exitString;

	public CLI(BufferedReader reader, PrintWriter writer, String exitString) {
		super();
		queue=new ArrayBlockingQueue<String>(200);
		this.reader = reader;
		this.writer = writer;
		this.exitString = exitString;
		this.isConnected=true;
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
	public void display(Character[][] board) {
		for(int i=0;i<board.length;i++){
		for(int j=0;j<board[i].length;j++)
			writer.print(board[i][j]);
		writer.println();
	}
	writer.flush();
		
	}

	public void close() {
		// TODO Auto-generated method stub
		
	}
	
	public void addMessageToQueue(String line) {
		this.queue.add(line);
	}

	@Override
	public void ClientIO(InputStream in, OutputStream out) throws IOException {
		this.writer=new PrintWriter(new OutputStreamWriter(out));
		this.reader=new BufferedReader(new InputStreamReader(in));
		Thread t=aSyncSendToClient(writer);
		reader.close();

		
	}
	private Thread aSyncSendToClient(PrintWriter writeToClient) {
		Thread t=new Thread(new Runnable() {
			
			@Override
			public void run() {
				sendToClient(writeToClient);
			}
		});
		t.start();	
		return t;
	}
	private void sendToClient(PrintWriter writeToClient){
		while(isConnected){
			try {
				String line=queue.take();
				if(line !=null){
					writeToClient.println(line);
					writeToClient.flush();
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
	}
}
