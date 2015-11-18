import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Created by user on 06.11.2015.
 */
public class testConnection {

	public static void main(String[] args) throws UnknownHostException,
			IOException {

		Socket sock = new Socket(InetAddress.getByName("127.0.0.1"),
				Protocol.PORT);
		Connection con = new Connection(sock);

		System.out.println("1. Send command");
		System.out.println("2. Receive command");
		System.out.println("3. Exit");
		con.accept();

	}

}
