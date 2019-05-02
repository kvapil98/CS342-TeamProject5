package project5_Client;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.util.function.Consumer;

public abstract class NetworkConnection {
	
	private ConnThread connthread = new ConnThread();
	private Consumer<Serializable> callback;
	int clientNumber;
	boolean playerNumSet = false;

	public NetworkConnection(Consumer<Serializable> callback) {
		this.callback = callback;
		connthread.setDaemon(true);
	}
	
	public void startConn() throws Exception{
		connthread.start();
	}
	
	public void send(Serializable data) throws Exception{
		connthread.out.writeObject(data);
	}
	
	public void sendString(String name) throws Exception{
		connthread.out.writeChars(name);
	}
	
	public void closeConn() throws Exception{
		connthread.socket.close();
	}
	
	abstract protected void setPort(int port);
	abstract protected void setIP(String ip);
	abstract protected String getIP();
	abstract protected int getPort();
	
	class ConnThread extends Thread{
		private Socket socket;
		private ObjectOutputStream out;
		
		public void run() {

				try(Socket socket = new Socket("127.0.0.1", 5555);
						ObjectOutputStream out = new ObjectOutputStream( socket.getOutputStream());
						ObjectInputStream in = new ObjectInputStream(socket.getInputStream())){
					
					this.socket = socket;
					this.out = out;
					socket.setTcpNoDelay(true);
					
					while(true) {
						Serializable data = (Serializable) in.readObject();
						callback.accept(data);
					}
					
				}
				catch(Exception e) {
					callback.accept("connection Closed");
				}
		}
	}
	
}
