/**
 * Created by user on 05.11.2015.
 */
public class NickCommand extends Command {

	private String nick;

	public NickCommand(CommandType type, String nick) {
		super(type);
		this.nick = nick;
	}

	public String intoString() {
		String s = "Connected to: " + this.nick + Protocol.LINE_END;
		return s;
	}

	public CommandType getType() {
		return type;
	}

	public void setType(CommandType type) {
		this.type = type;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}
}
