import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by Julia on 10.11.2015.
 */
public class MainForm implements Observer {
 
    final JFrame frame = new JFrame("ChatApp 1.0");
    final JPanel panel1 = new JPanel(); //главная панель
    final JPanel panel2 = new JPanel(); //верхняя панель для ввода своего ника и ip друга
    final JPanel panel3 = new JPanel(); //панель для ввода своего ника
    final JPanel panel4 = new JPanel(); //панель для ввода ip друга
    final JPanel panel5 = new JPanel(); //панель для ввода текста
    final JPanel panel6 = new JPanel(); //панель для кнопочек "Connect" и "Disconnect"
    final JPanel panel7 = new JPanel();
    final JLabel forText1 = new JLabel();
    final JLabel forText2 = new JLabel();
    final TextField textField1 = new TextField(35);//для ввода своего ника
    final TextField textField2 = new TextField(35); //для ввода ip друга
    final JTextArea textArea = new JTextArea(); //для вывода сообщения
    final JTextArea textAreaForMessenger = new JTextArea();//для ввода сообщения
    final JButton apply = new JButton("Apply");
    final JButton connect = new JButton("Connect");
    final JButton send = new JButton("Send");
    final JButton disconnect = new JButton("Disconnect");
    final JScrollPane areaScrollPane = new JScrollPane(textArea);
    public String nickname;
    public String ip;
    public boolean check; //проверка для блокировки кнопок "Connect" и "Disconnect"
    
    private CommandListenerThread comThread;
    private CallListenerThread callThread;
    public static Observer obj;

    public static void main(String args[]){
        SwingUtilities.invokeLater(new Runnable(){
            public void run(){
                new MainForm();
            }
        });
    }


 
    public MainForm(){
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
     //   panel1.setBackground(color);
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
      //  Color color2 = new Color(255, 255, 255);
      //  panel5.setBackground(color2);
        
        panel5.setMaximumSize(new Dimension(screenWidth /4, screenHeight / 25));
        panel5.setPreferredSize(new Dimension(screenWidth /4 ,screenHeight / 25));
        panel5.setMinimumSize(new Dimension(screenWidth/4,screenHeight / 25));
        
        panel5.setLayout(new BoxLayout(panel5, BoxLayout.X_AXIS));

        panel5.add(new JScrollPane(textAreaForMessenger));
        panel5.add(send);
        
        
        
        //панель для вывода сообщений на экран
       // Color color3 = new Color(255, 255, 255);
       // panel6.setBackground(color3);

        panel7.setMaximumSize(new Dimension(400, 300));
        panel7.setPreferredSize(new Dimension(400 ,300));
        panel7.setMinimumSize(new Dimension(400,300));

        panel7.setLayout(new BoxLayout(panel7, BoxLayout.X_AXIS));

        textArea.setMaximumSize(new Dimension(400, 300));
        textArea.setPreferredSize(new Dimension(400, 300));
        textArea.setMinimumSize(new Dimension(400,300));

        areaScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        panel7.add(new JScrollPane(textArea));
        textArea.setEditable(false);


        //панель для кнопочек
        panel6.setLayout(new BoxLayout(panel6, BoxLayout.LINE_AXIS));
        panel6.add(connect);
        panel6.add(disconnect);



        //действия для кнопочек
        apply.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                nickname = textField1.getText();
                textArea.append("   Your nickname: " + nickname + "\n");

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
						comThread = new CommandListenerThread(con);
						comThread.addObserver(obj);
						comThread.start();
					}
					else{
						textArea.append("  could not connect ip addr: " + ip +"\n");
					}
				} catch (UnknownHostException e1) {
					//e1.printStackTrace();
					//////
				}
            }
        });


        send.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String messenger = textAreaForMessenger.getText();

                long curTime = System.currentTimeMillis();
                String time = new SimpleDateFormat("HH:mm:ss").format(curTime);

                textArea.append("\n"  + "   " + nickname + " " + time + ":" + "\n" + " " + messenger + "\n");
                try {
					comThread.getConnection().sendMessage(messenger);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            }
        });


        disconnect.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //какое-то действие

                disconnect.setEnabled(false);
                connect.setEnabled(true);
            }
        });



        // добавляем на frame
        frame.add(panel1);
        panel1.add(panel2, BorderLayout.NORTH);
        panel1.add(panel5, BorderLayout.SOUTH);
        panel1.add(panel7);
        panel2.add(panel3);
        panel2.add(panel4);
        panel4.add(panel6);

        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }



	@Override
	public void update(Observable o, Object arg) {
		if (o.getClass()==CommandListenerThread.class){
			textArea.append(arg.toString());
		}
		else {
			//if observable == calllistenerThread do ...
		}
		
	}
}