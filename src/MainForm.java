import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by Julia on 10.11.2015.
 */
public class MainForm implements Observer {

	final JFrame frame = new JFrame("ChatApp 2.0");
	final JFrame frame1 = new JFrame(); // "всплывающее" окошко для соединения
										// или отказа в соединении "звонящему"
	final JPanel panel1 = new JPanel(); // главная панель
	final JPanel panel2 = new JPanel(); // верхняя панель для ввода своего ника
										// и ip друга
	final JPanel panel3 = new JPanel(); // панель для ввода своего ника
	final JPanel panel4 = new JPanel(); // панель для ввода ip друга
	final JPanel panel5 = new JPanel(); // панель для ввода текста
	final JPanel panel6 = new JPanel(); // панель для кнопочек "Connect" и
										// "Disconnect"
	final JPanel panel7 = new JPanel(); // панель для вывода сообщений на экран
	final JPanel panel8 = new JPanel(); // панель для кнопочек "всплывающег окна
	final JPanel panel9 = new JPanel(); // панель для ввода сообщений и вывода
										// сообщений
	final JPanel panel10 = new JPanel();
	final JLabel forText1 = new JLabel();
	final JLabel forText2 = new JLabel();
	final JLabel forText3 = new JLabel();
	final JLabel forText4 = new JLabel();
	final TextField textField1 = new TextField(35);// для ввода своего ника
	final TextField textField2 = new TextField(35); // для ввода ip друга
	final JTextArea textArea = new JTextArea(); // для вывода сообщения
	final JTextArea textAreaForMessenger = new JTextArea();// для ввода
															// сообщения
	final JLabel forImage = new JLabel();
	final JButton apply = new JButton("Change");
	final JButton connect = new JButton("Connect");
	final JButton send = new JButton("Send");
	final JButton disconnect = new JButton("Disconnect");
	final JButton accept = new JButton("Accept");
	final JButton reject = new JButton("Reject");
	final JButton addCombo = new JButton("Add");
	final JButton conCombo = new JButton("Con");
	final JScrollPane areaScrollPane = new JScrollPane(textArea);

	public String nickname;
	public String ip;

	private CommandListenerThread comThread;
	private CallListenerThread callThread;
	public static Observer obj;

	final TextField friendNick = new TextField(35);

	
	private ComboBoxModel cModel = new ComboBoxModel();;
	private ComboBoxView сView = new ComboBoxView(cModel);
	

	public static void main(String args[]) {
		try {
			UIManager
					.setLookAndFeel("com.seaglasslookandfeel.SeaGlassLookAndFeel");
		} catch (Exception e) {
			e.printStackTrace();
		}

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try {
					new MainForm();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
	}

	public MainForm() throws IOException {
		
		cModel.addObserver(сView);
		
		obj = this;

		// сама форма становится наблюдателем
		callThread = new CallListenerThread();
		callThread.start();

		// создание рамки исходя из размеров монитора по цетру экрана

		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		int screenWidth = screenSize.width;
		int screenHeight = screenSize.height;
	/*	frame.setSize(screenWidth / 2, screenWidth / 2);
		frame.setLocationRelativeTo(null);*/

		Image img = kit.getImage("caticon.png");
		frame.setIconImage(img);

		// главная палень на которой будут лежать остальные
		/*
		 * ImageIcon picture = new ImageIcon("416658223.jpg");
		 * forImage.setIcon(picture);
		 */
		panel1.setLayout(new BorderLayout());

		// вверхняя панель для ввода ника и ip друга
		panel2.setOpaque(false);
		//panel2.setMaximumSize(new Dimension(screenWidth, screenHeight));
		panel2.setPreferredSize(new Dimension(screenWidth / 2, screenHeight / 7));
		panel2.setLayout(new BoxLayout(panel2, BoxLayout.X_AXIS));

		// панель для своего ника
		panel3.setOpaque(false);
		panel3.add(Box.createVerticalStrut(15));
		panel3.setLayout(new BoxLayout(panel3, BoxLayout.Y_AXIS));

		panel3.setPreferredSize(new Dimension(screenWidth / 8, screenHeight / 8));
		panel3.setMaximumSize(new Dimension(screenWidth / 4, screenHeight / 4));
		panel3.setMinimumSize(new Dimension(screenWidth / 8, screenHeight / 8));
		panel3.setOpaque(false);

		forText1.setText("My nickname: ");
		panel3.add(forText1);
		textField1.setMaximumSize(new Dimension(200, 25));
		panel3.add(textField1);
		panel3.add(apply);

		// панель для ip друга
		panel4.setOpaque(false);
		panel4.add(Box.createVerticalStrut(15));
		panel4.setLayout(new BoxLayout(panel4, BoxLayout.Y_AXIS));

		panel4.setPreferredSize(new Dimension(screenWidth / 8, screenHeight / 8));
		panel4.setMaximumSize(new Dimension(screenWidth / 4, screenHeight / 4));
		panel4.setMinimumSize(new Dimension(screenWidth / 8, screenHeight / 8));
		panel4.setOpaque(false);

		forText2.setText("Remote addr:");
		panel4.add(forText2);
		textField2.setMaximumSize(new Dimension(200, 25));
		panel4.add(textField2, BorderLayout.SOUTH);

		// панель ввода текста
		panel5.setOpaque(false);
		panel5.setMaximumSize(new Dimension(650, screenHeight / 25));
	    panel5.setPreferredSize(new Dimension(650 ,screenHeight / 25));
	    panel5.setMinimumSize(new Dimension(650,screenHeight / 25));panel5.setLayout(new BoxLayout(panel5, BoxLayout.X_AXIS));

		panel5.add(new JScrollPane(textAreaForMessenger));
		send.setEnabled(false);
		panel5.add(send);

		// панель для вывода сообщений на экран
		panel7.setOpaque(false);
		/*panel7.setMaximumSize(new Dimension(screenWidth / 2, 500));
		panel7.setPreferredSize(new Dimension(screenWidth / 2, 500));
		panel7.setMinimumSize(new Dimension(screenWidth / 2, 500));
*/
		panel7.setLayout(new BorderLayout());

		textArea.setMaximumSize(new Dimension(650, 500));
	    textArea.setPreferredSize(new Dimension(650, 500));
	    textArea.setMinimumSize(new Dimension(650,500));

		areaScrollPane
				.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

		textArea.setWrapStyleWord(true);
	    textArea.setLineWrap(true);
		panel7.add(new JScrollPane(textArea));
		textArea.setEditable(false);

		// панель для кнопочек
		panel6.setOpaque(false);
		panel6.setLayout(new BoxLayout(panel6, BoxLayout.LINE_AXIS));
		disconnect.setEnabled(false);
		panel6.add(connect);
		panel6.add(disconnect);

		Protocol.serverCon
				.setServerAddress("jdbc:mysql://files.litvinov.in.ua/chatapp_server?characterEncoding=utf-8&useUnicode=true");
		Protocol.serverCon.connect();
		if (Protocol.serverCon.isConnected() == false) {
			textArea.append("[System] Could not connect to the server" + "\n");
		} else {
			textArea.append("[System] You connected to the server" + "\n");
		}

		Protocol.NICK = "unnamed";
		textArea.append("[System] Your current nick is unnamed. "
				+ "\n"
				+ "You may change it by writing a new nickname in the field above and clicking the Change button."
				+ "\n");

		Protocol.serverCon.setLocalNick(Protocol.NICK);
		Protocol.serverCon.goOnline();

		// действия для кнопочек
		apply.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Protocol.NICK = textField1.getText();
				Protocol.serverCon.setLocalNick(Protocol.NICK);
				textArea.append("[System] Nickname changed to: "
						+ Protocol.NICK + "\n");

				apply.setEnabled(false);
			}
		});

		connect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// addCombo.setEnabled(true);
				// delCombo.setEnabled(true);

				ip = textField2.getText();
				textArea.append("   ip addr: " + ip + "\n");

				connect.setEnabled(false);
				disconnect.setEnabled(true);

				try {
					Caller caller = new Caller(ip);
					Connection con = caller.call();
					if (con != null) {
						comThread = new CommandListenerThread(con);
						comThread.addObserver(MainForm.this);
						comThread.start();
                        textArea.append("Waiting for accept...");
                        //TODO
                        //тут поставить таймер

					} else {
						textArea.append("  Could not connect ip addr: " + ip
								+ "\n");
						connect.setEnabled(true);
						disconnect.setEnabled(false);
						send.setEnabled(false);
						apply.setEnabled(true);
					}
				} catch (UnknownHostException e1) {
					// e1.printStackTrace();
					// ////
				}
			}
		});

		send.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String messenger = textAreaForMessenger.getText();

				long curTime = System.currentTimeMillis();
				String time = new SimpleDateFormat("HH:mm:ss").format(curTime);

				textArea.append("\n" + "   " + Protocol.NICK + " " + time + ":"
						+ "\n" + "   " + messenger + "\n");
				textAreaForMessenger.setText("");
				try {
					// callThread.getConnection().sendMessage(messenger);
					if (comThread != null) {
						comThread.getConnection().sendMessage(messenger);
					} else {
						callThread.getConnection().sendMessage(messenger);
						// maybe change to -
						// textArea.append("Looks like we lost him :c"+Protocol.LINE_END);
						// ??
					}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		disconnect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (comThread != null) {
					try {
						comThread.getConnection().disconnect();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}

				comThread.stop();
				comThread = null;

				send.setEnabled(false);
				disconnect.setEnabled(false);
				connect.setEnabled(true);
				apply.setEnabled(true);
			}
		});



		// "всплывающее" окошко для соединения или отказа в соединении
		// "звонящему"
		frame1.setLocationRelativeTo(null);

		panel8.add(accept);
		panel8.add(reject);

		forText3.setText("User " + nickname + " tries to connect to you" + "\n");
		frame1.add(forText3);

		frame1.setSize(200, 100);
		frame1.add(panel8, BorderLayout.SOUTH);
		frame1.setAlwaysOnTop(true);
		frame1.setVisible(false); // будет тру когда звонят

		accept.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// если соединился
			}
		});

		reject.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// если отказался
			}
		});

		// для ввода и вывода сообщений
		panel9.setPreferredSize(new Dimension(screenWidth / 2, 600));
		panel9.setMaximumSize(new Dimension(screenWidth / 2, 600));
		panel9.setMinimumSize(new Dimension(screenWidth / 2, 600));

		
		// для списка контактов
		panel10.setPreferredSize(new Dimension(300, 600));
		panel10.setMaximumSize(new Dimension(300, 600));
		panel10.setMinimumSize(new Dimension(300, 600));

		forText4.setText("Your contact list: ");
		panel10.add(forText4);
		panel10.add(сView);

		//сView.setEditable(true); 
		сView.setAlignmentY(240);
		сView.setPreferredSize(new Dimension(270, 30));
		сView.setVisible(true);

		panel10.add(conCombo);
		panel10.add(addCombo);
		panel10.add(friendNick);
		// addCombo.setEnabled(false); delCombo.setEnabled(false);

		addCombo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (friendNick.getText().length() > 0) {
					if (Protocol.serverCon.isNickOnline(friendNick.getText())) {
						String nick = friendNick.getText();
						textArea.append(nick + " is online"+ Protocol.LINE_END);
						cModel.addUser(nick,Protocol.serverCon.getIpForNick(nick),Protocol.serverCon.isNickOnline(nick));
						
						try {
							FileOutputStream fos = new FileOutputStream("temp.out");
							 ObjectOutputStream oos = new ObjectOutputStream(fos);
							 oos.writeObject(cModel.getUser(cModel.getSize()-1));
							 oos.flush();
							 oos.close();
							 FileInputStream fis = new FileInputStream("temp.out");
							  ObjectInputStream oin = new ObjectInputStream(fis);
							  ComboBoxModel.User u = (ComboBoxModel.User) oin.readObject();
							  textArea.append("version="+u.getIp());
						} catch (FileNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (ClassNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						 
					} else {
						textArea.append(friendNick.getText() + " is offline"
								+ Protocol.LINE_END);
					}
					
				}
			}
		});

		conCombo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				connect.setEnabled(false);
				disconnect.setEnabled(true);
				String [] arr;
				arr = сView.getSelectedItem().toString().split(" ");
				textArea.append(arr[2]);
				
				try {
					Caller caller = new Caller(arr[2]);
					Connection con = caller.call();
					if (con != null) {
						send.setEnabled(true);
						comThread = new CommandListenerThread(con);
						comThread.addObserver(MainForm.this);
						comThread.start();
						textArea.append("Waiting for accept...");
						//тут добавить таймер

					} else {
						textArea.append("  could not connect ip addr: " + arr[2]
								+ "\n");
						connect.setEnabled(true);
						disconnect.setEnabled(false);
						send.setEnabled(false);
						apply.setEnabled(true);
					}
				} catch (UnknownHostException e1) {
					// e1.printStackTrace();
					// ////
				}
			}
		});

        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                Protocol.serverCon.goOffline();
                System.exit(0);
            }
        });

		// добавляем на frame
		panel9.add(panel7, BorderLayout.CENTER);
		panel9.add(panel5, BorderLayout.SOUTH);
		frame.add(panel1);
		panel1.add(panel9, BorderLayout.CENTER);
		panel1.add(panel10, BorderLayout.EAST);

		panel1.add(panel2, BorderLayout.NORTH);
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

		if (arg instanceof NickCommand) {
			nickcom = (NickCommand) arg;
			// nickcom.getNick();
			textArea.append(nickcom.intoString() + Protocol.LINE_END);
		}
		if (arg instanceof MessageCommand) {
			mescom = (MessageCommand) arg;

			long curTime = System.currentTimeMillis();
			String time = new SimpleDateFormat("HH:mm:ss").format(curTime);

			textArea.append("\n" + "   " + Protocol.curNick + " " + time + ":"
					+ "\n" + "   " + mescom.getMsg() + "\n");
			// textArea.append("Incoming Message: "+mescom.getMsg()+Protocol.LINE_END);
		}
		if (arg instanceof Command) {
			com = (Command) arg;
			if (com.getType() == Command.CommandType.ACCEPT) {
				// TODO
				// начать беседу
				connect.setEnabled(false);
				disconnect.setEnabled(true);
				send.setEnabled(true);
				apply.setEnabled(false);
                textArea.append("You can start the conversation now");
			}

			if (com.getType() == Command.CommandType.REJECT) {
				// TODO
				textArea.append("It seems like " + Protocol.curNick
						+ " doesn't want to talk to you :c" + Protocol.LINE_END);
				// убить ComListThread
				connect.setEnabled(true);
				disconnect.setEnabled(false);
				send.setEnabled(false);
				apply.setEnabled(true);
			}

			if (com.getType() == Command.CommandType.DISCONNECT) {
				textArea.append("Looks like we lost him :c" + Protocol.LINE_END);
				// убить ComListThread
				connect.setEnabled(true);
				disconnect.setEnabled(false);
				send.setEnabled(false);
				apply.setEnabled(true);
			}

			if (com.getType() == Command.CommandType.BUSY) {
				textArea.append(com.toString());
				// убить ComListThread
				connect.setEnabled(true);
				disconnect.setEnabled(false);
				send.setEnabled(false);
				apply.setEnabled(true);
			}

		}
	}
}