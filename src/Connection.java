import java.io.*;
import java.net.Socket;

public class Connection {

	private Socket socket;

	private boolean open;

	OutputStream sout;
	PrintWriter pount;

	public Connection(Socket socket) throws IOException {
		this.socket = socket;
		 sout= socket.getOutputStream();
		 pount = new PrintWriter(sout, true);}




	public void accept() throws IOException {
		pount.println("Accepted");
		pount.flush();
		//pount.close();
		//sout.close();
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
		String type = reader.readLine();
		Command c = Command.createCommand(type);
		return c;
	}
}
