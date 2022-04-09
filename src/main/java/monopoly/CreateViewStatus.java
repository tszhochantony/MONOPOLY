package monopoly;
public class CreateViewStatus extends CommandFactory{
    public Command createCommand(){
        return new ViewStatus();
    }
}