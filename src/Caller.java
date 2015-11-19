import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;



/**
 * Created by user on 10.11.2015.
 */
public class Caller {
	InetAddress ipAddr;
	
	public Caller(String ip) throws UnknownHostException{
		ipAddr = InetAddress.getByName(ip);
	}
	
    Connection call(){
        try {
            Socket socket = new Socket(ipAddr,Protocol.PORT);
            Connection conn = new Connection(socket);
            conn.sendNickHello(Protocol.NICK);
            return conn;
          //  if (conn.receive().getType()== Command.CommandType.NICK){return conn;}

        }
        catch (IOException ioe){ioe.printStackTrace();}

        return null;
    }
}
