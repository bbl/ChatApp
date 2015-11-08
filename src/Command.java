/**
 * Created by user on 05.11.2015.
 */
public class Command {

    Command(){}

    Command(String type){
        this.type=type;
    }


    public String intoString(String stringEnd){
        String s=type+stringEnd;
        return s;
    }


    private boolean isUnableToRecognize;          //если CommandListener вытащил коммаду, она ушла в интерпретатор, но там не нашлось
    public boolean isUnableToRecognize() {
        return isUnableToRecognize;}  //метода для обработки такой команды, то выставляется true и комманда отправляется обратно в очередь
    public void setIsUnableToRecognize(boolean isUnableToRecognize) {
        this.isUnableToRecognize = isUnableToRecognize;
    }

    private String type;
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
}
