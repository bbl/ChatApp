/**
 * Created by user on 05.11.2015.
 */
public class NickCommand extends Command{

    NickCommand(String type,String nick){
        this.nick=nick;
        this.type=type;
    }

    public String intoString(String nick, String stringEnd){
        String s=type+' '+nick+stringEnd;
        return s;
    }


    private String type;
    private String nick;

    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getNick() {
        return nick;
    }
    public void setNick(String nick) {
        this.nick = nick;
    }
}
