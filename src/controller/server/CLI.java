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

import org.hibernate.engine.spi.SessionDelegatorBaseImpl;

public class CLI extends Observable implements ClientHandler {
	private ArrayBlockingQueue<String> queue;
	private volatile boolean isConnected;
	private String exitString;

	public CLI() {
		queue = new ArrayBlockingQueue<String>(200);
		this.queue.clear();
		this.isConnected = true;
	}

	
	@Override
	public void display(Character[][] board) {
		for(int i=0;i<board.length;i++){
			this.addMessageToQueue(board[i].toString());
		}
		
	}
	@Override
	public void display(Character[][] board,Integer steps) {
		for(int i=0;i<board.length;i++){
			this.addMessageToQueue(board[i].toString());
		}
		this.addMessageToQueue(steps.toString());
		
	}


	public void addMessageToQueue(String line) {
		this.queue.add(line);
	}

	@Override
	public void ClientIO(InputStream in, OutputStream out) throws IOException {

		try {
			this.queue = new ArrayBlockingQueue<String>(20);
			this.queue.clear();
			this.isConnected = true;
			// Adapter from InputStream to BufferReader
			BufferedReader clientInput = new BufferedReader(new InputStreamReader(in));
			PrintWriter serverOutput = new PrintWriter(out);

			// open a new thread who reading from the client
			Thread fromClient = aSyncReadInputs(clientInput, "exit");
			Thread toClient = aSyncSendToClient(serverOutput);

			fromClient.join();
			toClient.join();
			clientInput.close();
			serverOutput.close();

		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private Thread aSyncReadInputs(BufferedReader in, String exitStr) {
		Thread t = new Thread(new Runnable() {

			@Override
			public void run() {
				readInputs(in, exitStr);
			}
		});
		t.start();
		return t;
	}

	private void readInputs(BufferedReader in, String exitStr) {

		String line;
		boolean flag = false;

		try {
			while (!flag) {

				line = in.readLine();

				if (line.equals(exitStr)) {
					flag = true;
					this.addMessageToQueue("Thank You For Playing!");
					stop();
				} else {
					setChanged();
					notifyObservers(line);
				}

			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private Thread aSyncSendToClient(PrintWriter writeToClient) {
		Thread t = new Thread(new Runnable() {

			@Override
			public void run() {
				sendToClient(writeToClient);
			}
		});
		t.start();
		return t;
	}

	public void sendMsgToClient(ArrayBlockingQueue<String> queue) {
		this.queue = queue;
	}

	private void sendToClient(PrintWriter writeToClient) {
		while (isConnected) {
			try {
				String line = queue.take();
				if (line != null) {
					writeToClient.println(line);
					writeToClient.flush();
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
	}
	private void stop(){
		this.isConnected=false;
	}
}
