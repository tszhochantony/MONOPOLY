package monopoly;
public class CreateViewGameRules extends CommandFactory{
    public Command createCommand(){
        return new ViewGameRules();
    }
}