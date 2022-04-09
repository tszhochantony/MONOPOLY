package monopoly;

import java.util.HashMap;
import java.util.Scanner;

/**
 * Hello world!
 */
public final class App {
    private App() {
    }

    /**
     * Says hello to the world.
     *
     * @param args The arguments of the program.
     */
    public static void main(String[] args) {
        Invoker invoker = new Invoker();
        Scanner sc = new Scanner(System.in);
        CommandFactory cf;
        Command c;
        HashMap<String, CommandFactory> map = new HashMap<String, CommandFactory>();
        map.put("i", new CreateInitialize());
        map.put("r", new CreateRollDice());
        map.put("v", new CreateViewStatus());
        map.put("vp", new CreateViewPersonalStatus());
        map.put("gr", new CreateViewGameRules());
        map.put("c", new CreateChangeName());
        map.put("s", new CreateSaveGame());
        map.put("l", new CreateLoadGame());

        cf = map.get("i");
        c = cf.createCommand();
        invoker.setCommand(c);
        invoker.invoke();
        for (;;) {
            String choice;
            System.out.println(
                    "Please select a action: \n " + "Roll dice - r ; View all status - v ; View personal status - vp ; \n"
                            + "View game rules - gr ; Change Name - c; \n"
                            + "Save game - s ; Load game - l ;   Exit game - e ;");
            choice = sc.next();
            try {
                if (choice.equals("e")) {
                    System.exit(0);
                }
                cf = map.get(choice);
                c = cf.createCommand();
                invoker.setCommand(c);
                invoker.invoke();
            } catch (NullPointerException e) {
                System.out.println("Please enter a valid command!\n\n");
            }
        }
    }
}
