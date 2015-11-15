import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;

/**
 * Created by user on 15.11.2015.
 */
public class CallListener{          //начат

    Socket socket;
    ServerSocket serverSocket;


   public Connection getConnection() throws IOException {
       serverSocket = new ServerSocket(Protocol.PORT);         // создаем server socket
       Socket socket = serverSocket.accept();                 // ждем вх. подключения

       //дождались
       Connection c = new Connection(socket);
       if (Protocol.isBusy == true) {                              //если при этом у нас занято
           c.sendNickBusy(Protocol.nick);
           c.disconnect();
           c=null;
       } else {
           c.sendNickHello(Protocol.nick);
           Command tmpCommand = c.receive();
           if (tmpCommand.type == Command.CommandType.NICK) {
               //вывести приветствие клиента на экран
           }
       }
       return c;
   }


    public void setBusy(boolean busy){
        Protocol.isBusy=busy;
    }


}
