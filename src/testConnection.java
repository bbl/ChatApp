import java.util.Scanner;

/**
 * Created by user on 06.11.2015.
 */
public class testConnection { //класс для тестирования Connection (временный)

    public static void main(String[] args) {
        int cs=0;

        while (cs!= 3) {
            System.out.println("1. Send command");
            System.out.println("2. Receive command");
            System.out.println("3. Exit");
            Scanner sc = new Scanner(System.in);
            cs = sc.nextInt();
            switch (cs) {
                case 1:{}
                case 2:{}
            }
        }



    }



}
