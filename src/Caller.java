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
            if (conn.receive().getType()== Command.CommandType.NICKHELLO){return conn;} // тип команды NICKHELLO пока не прописан

        }
        catch (IOException ioe){ioe.printStackTrace();}

        return null;
    }
}
