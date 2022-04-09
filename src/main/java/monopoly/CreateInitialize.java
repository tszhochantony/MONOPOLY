package monopoly;
public class CreateInitialize extends CommandFactory{
    public Command createCommand(){
        return new Initialize();
    }
}