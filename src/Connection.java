import java.io.*;
import java.net.Socket;

public class Connection {

	private Socket socket;

	private boolean open;

	private Connection(Socket socket) {
		this.socket = socket;
	}

	public void accept() throws IOException {
		OutputStream sout = socket.getOutputStream();
		PrintWriter pount = new PrintWriter(sout, true);
		pount.print("Accepted");
	}

	public void reject() throws IOException {
		OutputStream sout = socket.getOutputStream();
		PrintWriter pount = new PrintWriter(sout, true);
		pount.print("Rejected");
	}

	public void sendNickHello(String nick) throws IOException{
		OutputStream sout = socket.getOutputStream();
		PrintWriter pount = new PrintWriter(sout, true);
		pount.print("ChatApp 2015 user %username%");  //вместо %username% будет использоваться соответствующий геттер 
	}
	public void sendNickBusy(String nick) throws IOException{
		OutputStream sout = socket.getOutputStream();
		PrintWriter pount = new PrintWriter(sout, true);
		pount.print("ChatApp 2015 user %username% busy"); 
	}
	public void sendMessage(String msg) throws IOException {
		OutputStream sout = socket.getOutputStream();
		PrintWriter pount = new PrintWriter(sout, true);
		pount.print("Message: " + msg);
	}

	public void disconnect() throws IOException {
		OutputStream sout = socket.getOutputStream();
		PrintWriter pount = new PrintWriter(sout, true);
		pount.print("Disconnect");
	}

	public boolean isOpen() {
		return open;
	}

	public Command receive() throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				socket.getInputStream(), "UTF-8"));
		String type = reader.toString();
		Command c = Command.createCommand(type);
		return c;
	}
}
