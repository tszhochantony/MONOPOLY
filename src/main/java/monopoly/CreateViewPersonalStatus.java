package monopoly;
public class CreateViewPersonalStatus extends CommandFactory{
    public Command createCommand(){
        return new ViewPersonalStatus();
    }
}