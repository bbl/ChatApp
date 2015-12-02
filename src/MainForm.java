import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Observable;
import java.util.Observer;


/**
 * Created by Julia on 10.11.2015.
 */
public class MainForm implements Observer {

    final JFrame frame = new JFrame("ChatApp 2.0");
    final JFrame frame1 = new JFrame(); //"всплывающее" окошко для соединения или отказа в соединении "звонящему"
    final JPanel panel1 = new JPanel(); //главная панель
    final JPanel panel2 = new JPanel(); //верхняя панель для ввода своего ника и ip друга
    final JPanel panel3 = new JPanel(); //панель для ввода своего ника
    final JPanel panel4 = new JPanel(); //панель для ввода ip друга
    final JPanel panel5 = new JPanel(); //панель для ввода текста
    final JPanel panel6 = new JPanel(); //панель для кнопочек "Connect" и "Disconnect"
    final JPanel panel7 = new JPanel();  //панель для вывода сообщений на экран
    final JPanel panel8 = new JPanel(); //панель для кнопочек "всплывающег окна
    final JLabel forText1 = new JLabel();
    final JLabel forText2 = new JLabel();
    final JLabel forText3 = new JLabel();
    final TextField textField1 = new TextField(35);//для ввода своего ника
    final TextField textField2 = new TextField(35); //для ввода ip друга
    final JTextArea textArea = new JTextArea(); //для вывода сообщения
    final JTextArea textAreaForMessenger = new JTextArea();//для ввода сообщения
    final JLabel forImage = new JLabel();
    final JButton apply = new JButton("Change");
    final JButton connect = new JButton("Connect");
    final JButton send = new JButton("Send");
    final JButton disconnect = new JButton("Disconnect");
    final JButton accept = new JButton("Accept");
    final JButton reject = new JButton("Reject");
    final JScrollPane areaScrollPane = new JScrollPane(textArea);
    public String nickname;
    public String ip;

    private CommandListenerThread comThread;
    private CallListenerThread callThread;
    public static Observer obj;

    public static void main(String args[]){
        SwingUtilities.invokeLater(new Runnable(){
            public void run(){
                try {
                    new MainForm();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }


    public MainForm() throws IOException {

        obj = this;

        //сама форма становится наблюдателем
        callThread = new CallListenerThread();
        callThread.start();

        // comThread = new CommandListenerThread();
//		
        //callThread.addObserver(this);

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
       // BufferedImage myPicture = ImageIO.read(new File("416658223.jpg"));
        //JLabel picLabel = new JLabel(new ImageIcon(myPicture));
      //  panel1.add(picLabel);
        
      /*  ImageIcon picture = new ImageIcon("416658223.jpg");
        forImage.setIcon(picture);*/
        panel1.setLayout(new BorderLayout());



        //вверхняя панель для ввода ника и ip друга
        panel2.setOpaque(false);
        panel2.setMaximumSize(new Dimension(screenWidth, screenHeight));
        panel2.setPreferredSize(new Dimension(screenWidth /2 ,screenHeight / 7));
        panel2.setLayout(new BoxLayout(panel2, BoxLayout.X_AXIS));



        //панель для своего ника
        panel3.setOpaque(false);
        panel3.add(Box.createVerticalStrut(15));
        panel3.setLayout(new BoxLayout(panel3, BoxLayout.Y_AXIS));

        panel3.setPreferredSize(new Dimension(screenWidth/8,screenHeight / 8));
        panel3.setMaximumSize(new Dimension(screenWidth/4,screenHeight / 4));
        panel3.setMinimumSize(new Dimension(screenWidth/8,screenHeight / 8));
        panel3.setOpaque(false);

        forText1.setText("My nickname: ");
        panel3.add(forText1);
        textField1.setMaximumSize(new Dimension(200 , 25));
        panel3.add(textField1);
        panel3.add(apply);





        //панель для  ip друга
        panel4.setOpaque(false);
        panel4.add(Box.createVerticalStrut(15));
        panel4.setLayout(new BoxLayout(panel4, BoxLayout.Y_AXIS));

        panel4.setPreferredSize(new Dimension(screenWidth/8,screenHeight / 8));
        panel4.setMaximumSize(new Dimension(screenWidth/4,screenHeight / 4));
        panel4.setMinimumSize(new Dimension(screenWidth/8,screenHeight / 8));
        panel4.setOpaque(false);

        forText2.setText("Remote addr: ");
        panel4.add(forText2);
        textField2.setMaximumSize(new Dimension(200 , 25));
        panel4.add(textField2, BorderLayout.SOUTH);





        //панель ввода текста
        panel5.setOpaque(false);
        panel5.setMaximumSize(new Dimension(screenWidth /4, screenHeight / 25));
        panel5.setPreferredSize(new Dimension(screenWidth /4 ,screenHeight / 25));
        panel5.setMinimumSize(new Dimension(screenWidth/4,screenHeight / 25));

        panel5.setLayout(new BoxLayout(panel5, BoxLayout.X_AXIS));

        panel5.add(new JScrollPane(textAreaForMessenger));
        send.setEnabled(false);
        panel5.add(send);




        //панель для вывода сообщений на экран
        panel7.setOpaque(false);
        panel7.setMaximumSize(new Dimension(400, 300));
        panel7.setPreferredSize(new Dimension(400 ,300));
        panel7.setMinimumSize(new Dimension(400,300));

        panel7.setLayout(new BorderLayout());

    /*    textArea.setMaximumSize(new Dimension(400, 300));
        textArea.setPreferredSize(new Dimension(400, 300));
        textArea.setMinimumSize(new Dimension(400,300));*/


        areaScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        panel7.add(new JScrollPane(textArea));
        textArea.setEditable(false);




        //панель для кнопочек
        panel6.setOpaque(false);
        panel6.setLayout(new BoxLayout(panel6, BoxLayout.LINE_AXIS));
        disconnect.setEnabled(false);
        panel6.add(connect);
        panel6.add(disconnect);


        Protocol.NICK="unnamed";
        textArea.append("[System] Your current nick is unnamed. " + "\n"+"You may change it by writing a new nickname in the field above and clicking the Change button."+ "\n");

        //действия для кнопочек
        apply.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Protocol.NICK = textField1.getText();
               // Protocol.NICK=nickname;
                textArea.append("   Your nickname: " + Protocol.NICK + "\n");

                apply.setEnabled(false);
            }
        });


        connect.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ip = textField2.getText();
                textArea.append("   ip addr: " + ip + "\n");

                connect.setEnabled(false);
                disconnect.setEnabled(true);

                try {
                    Caller caller = new Caller(ip);
                    Connection con = caller.call();
                    if (con!=null){
                        send.setEnabled(true);
                        comThread = new CommandListenerThread(con);
                        comThread.addObserver(MainForm.this);
                        comThread.start();

                    }
                    else{
                        textArea.append("  could not connect ip addr: " + ip +"\n");
                        connect.setEnabled(true);
                        disconnect.setEnabled(false);
                        send.setEnabled(false);
                        apply.setEnabled(true);
                    }
                } catch (UnknownHostException e1) {
                    //e1.printStackTrace();
                    //////
                }
            }
        });


        send.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {


              /*  if(check=true){
                    send.setEnabled(true);
                }
                else send.setEnabled(false);*/


                String messenger = textAreaForMessenger.getText();

                long curTime = System.currentTimeMillis();
                String time = new SimpleDateFormat("HH:mm:ss").format(curTime);

                textArea.append("\n"  + "   " + nickname + " " + time + ":" + "\n" + "   " + messenger + "\n");
                textAreaForMessenger.setText("");
                try {
                    //callThread.getConnection().sendMessage(messenger);
                    if(comThread!=null){
                        comThread.getConnection().sendMessage(messenger);}
                    else{
                        callThread.getConnection().sendMessage(messenger);
                        // maybe change to -  textArea.append("Looks like we lost him :c"+Protocol.LINE_END); ??
                    }
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
        });


        disconnect.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if(comThread!=null){
                    try {
                        comThread.getConnection().disconnect();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }

                comThread.stop();
                comThread=null;

                send.setEnabled(false);
                disconnect.setEnabled(false);
                connect.setEnabled(true);
                apply.setEnabled(true);
            }
        });
        
        
        
        //"всплывающее" окошко для соединения или отказа в соединении "звонящему"
        frame1.setLocationRelativeTo(null);
      
        panel8.add(accept);
        panel8.add(reject);
        
        forText3.setText("User " + nickname + " tries to connect to you" + "\n");
        frame1.add(forText3);
        
        frame1.setSize(200, 100);
        frame1.add(panel8, BorderLayout.SOUTH);
        frame1.setAlwaysOnTop(true);
      //  frame1.pack();
        frame1.setVisible(false); //будет тру когда звонят
        
        
        accept.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            		//если соединился 
            }
        });
        
        reject.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            		//если отказался
            }
        });
        



        // добавляем на frame
        frame.add(panel1);
       // panel1.add(forImage);
        panel1.add(panel2, BorderLayout.NORTH);
        panel1.add(panel5, BorderLayout.SOUTH);
        panel1.add(panel7, BorderLayout.CENTER);
        panel2.add(panel3);
        panel2.add(panel4);
        panel4.add(panel6);
        
        
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }



    @Override
    public void update(Observable o, Object arg) {
        send.setEnabled(true);
        connect.setEnabled(false);

        NickCommand nickcom;
        MessageCommand mescom;
        Command com;

        if(arg instanceof NickCommand){
            nickcom=(NickCommand) arg;
            //nickcom.getNick();
            textArea.append(nickcom.intoString()+Protocol.LINE_END);
        }
        if(arg instanceof MessageCommand){
            mescom=(MessageCommand) arg;
            textArea.append("Incoming Message: "+mescom.getMsg()+Protocol.LINE_END);
        }
        if(arg instanceof Command){
            com =(Command) arg;
            if (com.getType()== Command.CommandType.ACCEPT){
                //TODO
                    //начать беседу
                    // перевести кнопки в режим разговора
            }

            if (com.getType()== Command.CommandType.REJECT){
                //TODO
                    // textArea.append("It seems like "+Protocol.curNick+" doesn't want to talk to you :c"+Protocol.LINE_END);
                    // убить ComListThread
                    // вернуть кнопки в режим ожидания
            }

            if (com.getType()== Command.CommandType.DISCONNECT){
                textArea.append("Looks like we lost him :c"+Protocol.LINE_END);
                // убить ComListThread
                // вернуть кнопки в режим ожидания
            }





        }
    }
}