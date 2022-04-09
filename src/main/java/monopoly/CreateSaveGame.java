package monopoly;
public class CreateSaveGame extends CommandFactory{
    public Command createCommand(){
        return new SaveGame();
    }
}