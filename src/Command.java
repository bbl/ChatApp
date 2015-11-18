/**
 * 
 */
public class Command {

	protected CommandType type;

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
		if (s!=null){
		if (s.contains("Accepted"))
			return new Command(CommandType.ACCEPT);
		else if (s.contains("Rejected"))
			return new Command(CommandType.REJECT);
		else if (s.contains("Disconnect"))
			return new Command(CommandType.DISCONNECT);
		else if (s.contains("ChatApp 2015"))
			return new NickCommand(CommandType.NICK,"передать в конструкор ник"); //доделать (убрать из сообщения все, кроме самого ника)
		else if (s.contains("Message"))                                          //аналогично
			return new Command(CommandType.MESSAGE);
		return null;
	}
		else return new Command(CommandType.DISCONNECT);
	}
}

// если клиент отправлял нам NickBusy (сообщение вида ChapApp 2015 user %username% busy), то она распознается тут как обычная nickCommand, a
// busy запишется в поле для ника (возможно придется переделывать )