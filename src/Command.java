/**
 * Created by user on 05.11.2015.
 */
public class Command {

    Command(){}

    Command(String type){
        this.type=type;
    }


    public String intoString(){
        String s=type+'\n';
        return s;
    }

    private String type;
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
}
