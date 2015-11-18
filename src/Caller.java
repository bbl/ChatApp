import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;



/**
 * Created by user on 10.11.2015.
 */
public class Caller {
    Connection call(InetAddress ipAddr, int PORT, String nick){
        try {
            Socket socket = new Socket(ipAddr, PORT);
            Connection conn = new Connection(socket);
            conn.sendNickHello(nick);
            if (conn.receive().getType()== NICK){return conn;}

        }
        catch (IOException ioe){ioe.printStackTrace();}

        return null;
    }
}
