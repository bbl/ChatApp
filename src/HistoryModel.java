import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Observable;

public class HistoryModel extends Observable {

	public static class Message {
		private String nick;
		private Date date;
		private String text;

		public Message(String nick, Date date, String text) {
			this.nick = nick;
			this.date = date;
			this.text = text;
		}

		public String getNick() {
			return nick;
		}

		public Date getDate() {
			return date;
		}

		public String getText() {
			return text;
		}
	}

	private List<Message> messages = new ArrayList<Message>();

	public int getSize() {
		return messages.size();
	}

	public Message getMessage(int pos) {
		return messages.get(pos);
	}

	public void addMessage(Message m) {
		messages.add(m);
		this.setChanged();
		this.notifyAll();
	}

	public void addMessage(String nick, Date date, String text) {
		messages.add(new Message(nick, date, text));
		this.setChanged();
		this.notifyAll();
	}

	public void clear() {
		messages.clear();
		this.setChanged();
		this.notifyAll();
	}
}
