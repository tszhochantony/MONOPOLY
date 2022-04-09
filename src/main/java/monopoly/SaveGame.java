package monopoly;

public class SaveGame implements Command {
    public void excute() {
        // Originator o = new Originator();
        // CareTaker ct = new CareTaker(o);
        g.saveGame();
    }
}
