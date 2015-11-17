import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Julia on 10.11.2015.
 */
public class MainForm {
 
    final JFrame frame = new JFrame("ChatApp 1.0");
    final JPanel panel1 = new JPanel(); //главная панель
    final JPanel panel2 = new JPanel(); //верхняя панель для ввода своего ника и ip друга
    final JPanel panel3 = new JPanel(); //панель для ввода своего ника
    final JPanel panel4 = new JPanel(); //панель для ввода ip друга
    final JPanel panel5 = new JPanel(); //панель для ввода текста
   // final JPanel panel6 = new JPanel(); // панель для вывода сообщений на экран
    final JPanel panel7= new JPanel(); //панель для кнопочек "Connect" и "Disconnect"
    final JLabel forText1 = new JLabel();
    final JLabel forText2 = new JLabel();
    final TextField textField1 = new TextField(35);//для ввода своего ника
    final TextField textField2 = new TextField(35); //для ввода ip друга
    final JTextArea textArea = new JTextArea(); //для ввода сообщения
    final JButton button1 = new JButton("Apply");
    final JButton button2 = new JButton("Connect");
    final JButton button3 = new JButton("Send");
    final JButton button4 = new JButton("Disconnect");
    final JLabel label = new JLabel(); //для вывода сообщений на экран


 
    public static void main(String args[]){
        SwingUtilities.invokeLater(new Runnable(){
            public void run(){
                new MainForm();
            }
        });
    }


 
    public MainForm(){

        //создание рамки исходя из размеров монитора по цетру экрана
 
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;
        frame.setSize(screenWidth / 2, screenWidth / 2);
        frame.setLocationRelativeTo(null);
 
        Image img = kit.getImage("caticon.png");
        frame.setIconImage(img);



        //главная палень на которой будут лежать остальные
      //  Color color = new Color(48, 40, 255);
       // panel1.setBackground(color);
        panel1.setLayout(new BorderLayout());



        //вверхняя панель для ввода ника и ip друга
       // Color color1 = new Color(86, 128, 255);
       // panel2.setBackground(color1);
        panel2.setMaximumSize(new Dimension(screenWidth, screenHeight));
        panel2.setPreferredSize(new Dimension(screenWidth /2 ,screenHeight / 7));
        panel2.setLayout(new BoxLayout(panel2, BoxLayout.X_AXIS));




        //панель для своего ника
        panel3.add(Box.createVerticalStrut(15));
        panel3.setLayout(new BoxLayout(panel3, BoxLayout.Y_AXIS));
 
        panel3.setPreferredSize(new Dimension(screenWidth/4,screenHeight / 4));
        panel3.setMaximumSize(new Dimension(screenWidth/2,screenHeight / 2));
        panel3.setMinimumSize(new Dimension(screenWidth/4,screenHeight / 4));
        panel3.setOpaque(false);

        forText1.setText("My nickname: ");
     //   forText1.setHorizontalAlignment(JTextField.RIGHT);
        panel3.add(forText1);
        textField1.setMaximumSize(new Dimension(200 , 25));
        panel3.add(textField1, BorderLayout.SOUTH);
        panel3.add(button1);




        //панель для  ip друга
        panel4.add(Box.createVerticalStrut(15));
        panel4.setLayout(new BoxLayout(panel4, BoxLayout.Y_AXIS));
 
        panel4.setPreferredSize(new Dimension(screenWidth/4,screenHeight / 4));
        panel4.setMaximumSize(new Dimension(screenWidth/2,screenHeight / 2));
        panel4.setMinimumSize(new Dimension(screenWidth/4,screenHeight / 4));
        panel4.setOpaque(false);


        forText2.setText("Remote addr: ");
      //  forText2.setHorizontalAlignment(JTextField.RIGHT);
        panel4.add(forText2);
        textField2.setMaximumSize(new Dimension(200 , 25));
        panel4.add(textField2, BorderLayout.SOUTH);




        //панель ввода текста
      //  Color color2 = new Color(255, 255, 255);
      //  panel5.setBackground(color2);
        
        panel5.setMaximumSize(new Dimension(screenWidth /4, screenHeight / 25));
        panel5.setPreferredSize(new Dimension(screenWidth /4 ,screenHeight / 25));
        panel5.setMinimumSize(new Dimension(screenWidth/4,screenHeight / 25));
        
        panel5.setLayout(new BoxLayout(panel5, BoxLayout.X_AXIS));

      /*  textArea.setMaximumSize(new Dimension(screenWidth, screenHeight));
        textArea.setPreferredSize(new Dimension(screenWidth/2, screenWidth/4));
        textArea.setMinimumSize(new Dimension(screenWidth/2,screenHeight/2));*/

        textArea.setCaretColor(Color.BLACK);
        textArea.setWrapStyleWord(true);
        textArea.setLineWrap(true);
        panel5.add(new JScrollPane(textArea));
        panel5.add(button3);
        
        
        
        //панель для вывода сообщений на экран
       // Color color3 = new Color(255, 255, 255);
       // panel6.setBackground(color3);
     /*
        panel6.setMaximumSize(new Dimension(screenWidth, screenHeight));
        panel6.setPreferredSize(new Dimension(screenWidth/2, screenWidth/4));
        panel6.setMinimumSize(new Dimension(screenWidth/2,screenHeight/2));
*/
        label.setMaximumSize(new Dimension(screenWidth, screenHeight));
        label.setPreferredSize(new Dimension(screenWidth/2, screenWidth/4));
        label.setMinimumSize(new Dimension(screenWidth/2,screenHeight/2));





        //панель для кнопочек
        panel7.setLayout(new BoxLayout(panel7, BoxLayout.LINE_AXIS));
        panel7.add(button2);
        panel7.add(button4);



//////////////////почитать про кнопочки!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

        //действия для кнопочек
        button1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nickname = textField1.getText();
                label.setText("Your nickname: " + nickname);

                //тестовое действие
            }
        });


        button2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String ip = textField2.getText();
                label.setText("ip addr: " + ip);

                //тестовое действие
            }
        });


        button3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String messenger = textField2.getText();
                label.setText(messenger); // не работает

                //тестовое действие
            }
        });

        button4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {


                //какое-то действие
            }
        });



        // добавляем на frame
        frame.add(panel1);
        panel1.add(panel2, BorderLayout.NORTH);
        panel1.add(panel5, BorderLayout.SOUTH);
        panel1.add(new JScrollPane(label));
       // panel1.add(label, BorderLayout.CENTER);
      //  panel1.add(panel6, BorderLayout.CENTER);
        panel2.add(panel3);
        panel2.add(panel4);
        panel4.add(panel7);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}