public class MessageCommand extends Command {

	private String msg;

	MessageCommand(CommandType type, String msg) {
		super(type);
		this.msg = msg;

	}

}
