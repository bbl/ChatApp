
import java.io.IOException;
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
		System.out.println("���� �������...");

		Socket socket = ss.accept();
		ss.close();
		Connection con = new Connection(socket);
		System.out.println("������ ���������");
		System.out.println();
		while (true) {
			System.out.println(con.receive().getType());
		}

	}

}
