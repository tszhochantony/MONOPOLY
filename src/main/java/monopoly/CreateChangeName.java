package monopoly;
public class CreateChangeName extends CommandFactory{
    public Command createCommand(){
        return new ChangeName();
    }
}