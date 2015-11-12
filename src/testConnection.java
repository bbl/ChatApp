import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 * Created by user on 06.11.2015.
 */
public class testConnection { 

    public static void main(String[] args) throws UnknownHostException, IOException {
        int cs=0;
        Socket sock = new Socket("files.litvinov.in.ua",Protocol.PORT);
        Connection con = new Connection(sock);
        while (cs!= 3) {
            System.out.println("1. Send command");
            System.out.println("2. Receive command");
            System.out.println("3. Exit");
            Scanner sc = new Scanner(System.in);
            cs = sc.nextInt();
            switch (cs) {
                case 1:{con.accept();System.out.println(con.receive().toString());}
                case 2:{}
            }
        }



    }



}
