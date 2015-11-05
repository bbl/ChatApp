/**
 * Создание экземпляра - Connection.getInstance(socket)
 * Использование методов - getConnection().method()
 */


import javax.xml.soap.SAAJResult;
import java.io.*;
import java.net.Socket;


public class Connection {          //в этом классе будет только 2 метода - принять строку (и преобр в команду) и отправить строку

	private Socket socket;
	
	private Connection(Socket socket){
		this.socket = socket;
	}

	public void send(Command c)throws IOException{
		String s;
		s=c.intoString();
		OutputStream sout = socket.getOutputStream();
		PrintWriter pount = new PrintWriter(sout,true);
		pount.print(s);
	}

	public Command receive() throws IOException {
		String type;
		InputStream sin = socket.getInputStream();
		//тут добавить Reader, записать в строку
        //определиться с видом команды (mesCommand,nickCommand или просто Command)
		//Command c = new Command(type);
		return c;
	}
}
