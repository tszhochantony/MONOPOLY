package monopoly;
public class CreateLoadGame extends CommandFactory{
    public Command createCommand(){
        return new LoadGame();
    }
}