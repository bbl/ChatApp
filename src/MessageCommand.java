public class MessageCommand extends Command {

	private String msg;

	MessageCommand(CommandType type, String msg) {
		super(type);
		this.setMsg(msg);

	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	

}
