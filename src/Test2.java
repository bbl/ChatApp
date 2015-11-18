import java.io.BufferedReader;

import java.io.IOException;

import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;


public class Test2 {

	/**
	 * @param args
	 * @throws IOException
	 * @throws UnknownHostException
	 */
	public static void main(String[] args) throws UnknownHostException,
			IOException {

		ServerSocket ss = new ServerSocket(Protocol.PORT);
		System.out.println("Ждем клиента...");

		Socket socket = ss.accept();
		Connection con = new Connection(socket);
		System.out.println("Клиент подключен");
		System.out.println();
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				socket.getInputStream(), "UTF-8"));
		while (true) {
			//con.receive().toString();
			System.out.println(con.isOpen());
			System.out.println(con.receive().toString());
		}

	}

}
