/**
 * 
 */
public class Command {

	private CommandType type;

	Command(CommandType type) {
		this.type = type;
	}

	public enum CommandType {
		ACCEPT, DISCONNECT, MESSAGE, NICK, REJECT;
	}

	public CommandType getType() {
		return type;
	}

	static Command createCommand(String s) {
		if (s.contains("Accepted"))
			return new Command(CommandType.ACCEPT);
		else if (s.contains("Rejected"))
			return new Command(CommandType.REJECT);
		else if (s.contains("Disconnect"))
			return new Command(CommandType.DISCONNECT);
		else if (s.contains("Nick"))
			return new Command(CommandType.NICK);
		else if (s.contains("Message"))
			return new Command(CommandType.MESSAGE);
		return null;
	}
}
