import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Observable;

/**
 * Created by user on 10.11.2015.
 */
public class CallListenerThread extends Observable implements Runnable  {   //пустой

    CallListener callListener;
    Socket socket;
    ServerSocket serverSocket;

    @Override
    public  void run(){  // В цикле вызывает getConnection у callListener'а и оповещает наблюдателей (из описания)
        try {
            while (true){
                serverSocket = new ServerSocket(Protocol.PORT);         // создаем server socket
                socket = serverSocket.accept();                 // ждем вх. подключения
                callListener = new CallListener();
                Connection conn = callListener.getConnection(socket);
                if (conn!=null) {
                    CommandListenerThread clt = new CommandListenerThread(conn);
                    clt.start();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void start(){}
    public void stop(){}



}




