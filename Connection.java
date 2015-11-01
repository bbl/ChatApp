/**
 * Создание экземпляра - Connection.getInstance(socket)
 * Использование методов - getConnection().method()
 */


import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;


public class Connection {
	
	private static Connection uniqueInstance;
	private Socket socket;
	
	private Connection(Socket socket){
		this.socket = socket;
	}
	
	public static synchronized Connection getInstance(Socket socket){
		if (uniqueInstance==null){
			uniqueInstance = new Connection(socket);
		}
		return uniqueInstance;
	}
	
	public static synchronized Connection getConnection(){
		return uniqueInstance;
	}
	
	public void sendNickHello(String nick) throws IOException{
		OutputStream sout = socket.getOutputStream();
        DataOutputStream out = new DataOutputStream(sout);
        out.writeUTF(nick);
        out.flush();
	}
	public void sendNickBusy(String ver, String nick) throws IOException{
		OutputStream sout = socket.getOutputStream();
        DataOutputStream out = new DataOutputStream(sout);
        out.writeUTF(nick);
        out.writeUTF(ver);
        out.flush();
	}
	
	public void accept(){
		
	}
	
	public void reject(){
		
	}
	public void disconnect(){
		
	}
	
	public void sendMessage(String msg) throws IOException{
		OutputStream sout = socket.getOutputStream();
        DataOutputStream out = new DataOutputStream(sout);
        out.writeUTF(msg);
        out.flush();
	}
	
	public Command receive(){
		return null;
	}
}
