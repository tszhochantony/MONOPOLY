package monopoly;
import java.util.*;
public class Invoker{
    Stack<Command> command = new Stack<Command>();
    public void setCommand(Command command){
        this.command.push(command);
    }
    
    public void invoke(){
        command.peek().excute();
    }
}
