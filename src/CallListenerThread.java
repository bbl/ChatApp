import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Observable;

/**
 * Created by user on 10.11.2015.
 */
public class CallListenerThread extends Observable implements Runnable  {   //пустой

    CallListener callListener = new CallListener();

    @Override
    public  void run(){  // В цикле вызывает getConnection у callListener'а и оповещает наблюдателей (из описания)
        try {
            while (callListener.getConnection()==null){

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void start(){}
    public void stop(){}



}




