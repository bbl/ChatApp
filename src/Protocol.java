import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by user on 06.11.2015.
 */
public class Protocol {      //тут будут всякие константы, шаблоны

    static Queue<Command> commandQueue = new LinkedList<Command>();  //очередь для комманд. CommandListener будет из отдельного потока
                                                                     //проверять, есть ли что в очереди




    public static final String stringEnd="\n";
    static final int port=28411;
 //   private static enum commandType { accept, reject  }
}
