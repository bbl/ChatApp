import javax.swing.*;
import java.awt.*;

/**
 * Created by Julia on 10.11.2015.
 */
public class MainForm {

    final JFrame frame = new JFrame("ChatApp 1.0");
    final JPanel panel1 = new JPanel(); //главная панель
    final JPanel panel2 = new JPanel(); //верхняя панель для ввода своего ника и ip друга

    public static void main(String args[]){
        SwingUtilities.invokeLater(new Runnable(){
            public void run(){
                new MainForm();
            }
        });
    }

    public MainForm(){

        //создание рамки исходя из размеров монитора по цетру экрана

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;
        frame.setSize(screenWidth / 2, screenWidth / 3);
        frame.setLocationRelativeTo(null);

        Image img = kit.getImage("caticon.png");
        frame.setIconImage(img);

        //главная палень на которой будут лежать остальные
        Color color = new Color(255, 255, 255);
        panel1.setBackground(color);
        panel1.setLayout(new BorderLayout());











        frame.setVisible(true);
    }
}
