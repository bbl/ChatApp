import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class ComboBoxModel extends Observable {
	private List<User> users = new ArrayList<User>();

	public static class User {
		String nick;
		boolean online;

		public User(String nick) {
			this.nick = nick;
		}

		public String getNick() {
			return nick;
		}

		public void setNick(String nick) {
			this.nick = nick;
		}

		public boolean isOnline() {
			return online;
		}

		public void setOnline() {
			this.online = true;
		}

	}

	public void addUser(String nick) {
		synchronized (this) {
			users.add(new User(nick));
			this.setChanged();
			this.notifyObservers();
			System.out.println(nick);
		}
	}

	public void deleteUser(int index) {
		synchronized (this) {
			users.remove(index);
			this.setChanged();
			this.notifyObservers();
		}
	}

	public User getUser(int index) {
		return users.get(index);
	}

	public int getSize() {
		return users.size();
	}

	public List<User> getAllUsers() {
		return users;
	}

}
