/**
 * 
 */
public class Command {

	protected CommandType type;

	Command(CommandType type) {
		this.type = type;
	}

	public enum CommandType {
		ACCEPT, DISCONNECT, MESSAGE, NICK, REJECT,BUSY;
	}

	public CommandType getType() {
		return type;
	}

	static Command createCommand(String s) {
		if (s!=null){
		if (s.contains("Accepted"))
			return new Command(CommandType.ACCEPT);
		else if (s.contains("Rejected"))
			return new Command(CommandType.REJECT);
		else if (s.contains("Disconnect"))
			return new Command(CommandType.DISCONNECT);
		else if (s.contains("ChatApp 2015")){
			if (s.contains("busy")) return new BusyCommand(CommandType.BUSY,s.replaceAll("ChatApp 2015 user ", ""));
			else return new NickCommand(CommandType.NICK,s.replaceAll("ChatApp 2015 user ", ""));}
		else if (s.contains("Message"))
			return new MessageCommand(CommandType.MESSAGE,s.replaceAll("Message", ""));

			return null;

	}
		else return null;
	}
}