package monopoly;
public class CreateRollDice extends CommandFactory{
    public Command createCommand(){
        return new RollDice();
    }
}