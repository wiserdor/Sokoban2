package controller.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public interface ClientHandler {
	public void ClientIO(InputStream in, OutputStream out) throws IOException;

	void start();

	void display(Character[][] board);
	
	
}
