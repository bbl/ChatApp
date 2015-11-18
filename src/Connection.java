import java.io.*;
import java.net.Socket;

public class Connection {

	private Socket socket;

	private boolean open;

	OutputStream sout;
	PrintWriter pount;

	BufferedReader reader;


	public Connection(Socket socket) throws IOException {
		this.socket = socket;
		 sout= socket.getOutputStream();
		 pount = new PrintWriter(sout, true);
		reader = new BufferedReader(new InputStreamReader(
				socket.getInputStream(), "UTF-8"));
		}




	public void accept() throws IOException {
		pount.println("Accepted"+Protocol.LINE_END);
		pount.flush();
	}

	public void reject() throws IOException {
		pount.print("Rejected"+Protocol.LINE_END);
		pount.flush();
	}

	public void sendNickHello(String nick) throws IOException{
		pount.print("ChatApp 2015 user "+Protocol.NICK);
		pount.flush();
	}

	public void sendNickBusy(String nick) throws IOException{
		pount.print("ChatApp 2015 user %username% busy");
		pount.flush();
	}

	public void sendMessage(String msg) throws IOException {
		pount.print("Message: " + msg);
		pount.flush();
	}

	public void disconnect() throws IOException {
		pount.print("Disconnect");
		pount.flush();
	}

	public boolean isOpen() {
		return open;
	}

	public Command receive() throws IOException {
		String type = reader.readLine();
		Command c = Command.createCommand(type);
		return c;

	}
}
