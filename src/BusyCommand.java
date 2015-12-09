/**
 * Created by user on 10.12.2015.
 */
public class BusyCommand extends Command {

    private String nick;

    public BusyCommand(CommandType type, String nick) {
        super(type);
        this.nick = nick;
    }

    public String intoString() {
        String s = "User " + this.nick +" is busy at that moment" + Protocol.LINE_END;
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

