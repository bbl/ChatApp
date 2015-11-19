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
    private volatile boolean disconnected;

    @Override
    public  void run(){  // В цикле вызывает getConnection у callListener'а и оповещает наблюдателей (из описания)
        try {
        	  serverSocket = new ServerSocket(Protocol.PORT); 
            while (true){
                      // создаем server socket
                socket = serverSocket.accept();                 // ждем вх. подключения
                callListener = new CallListener();
                Connection conn = callListener.getConnection(socket);
                if (conn!=null) {
                    CommandListenerThread clt = new CommandListenerThread(conn);
                    clt.addObserver(MainForm.obj);
                    clt.start();
                    
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    void start() {
        this.disconnected = false;
        Thread t = new Thread(this);
        t.start();
    }

    boolean isDisconnected() {
        return disconnected;
    }

    void stop() {
        disconnected = true;
    }



}




