import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class ComboBoxModel extends Observable {
	private List<User> users = new ArrayList<User>();

	public static class User implements Serializable{
		String nick;
		String ip;
		boolean online;

		public User(String nick, String ip, boolean online) {
			this.nick = nick;
			this.ip = ip;
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

		public String getIp() {
			return ip;
		}

	}

	public User addUser(String nick, String ip, boolean online) {
		synchronized (this) {
			users.add(new User(nick, ip, online ));
			this.setChanged();
			this.notifyObservers();
			System.out.println(nick);
			return users.get(getSize()-1);
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
