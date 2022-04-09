package monopoly;

import java.util.concurrent.ThreadLocalRandom;
import java.util.*;
import java.io.*;

public class Game {
    int location;
    int dice1;
    int dice2;
    int round = 1;
    int numOfPlayer = 0;
    int currentPlayer = 0;
    Property p;
    ArrayList<Map> map = new ArrayList<Map>();
    ArrayList<Integer> save = new ArrayList<Integer>();
    ArrayList<Player> players = new ArrayList<Player>();
    GoToJail jail = new GoToJail();
    PrintGameBoard pgb;
    Scanner sc = new Scanner(System.in);

    public void newGame() {
        boolean checker = false;
        System.out.println("Welcome to play Monopoly!");
        while (!checker) {
            System.out.println("Please input a number of players");
            try {
                numOfPlayer = sc.nextInt();
                checker = checkPlayerNum(numOfPlayer);
                if (!checker) {
                    System.out.println("Please input a number between 2 to 6!");
                }
            } catch (Exception e) {
                System.out.println("Please input a number!");
                sc.nextLine();
            }
        }
        map.add(new Go());
        map.add(new Central());
        map.add(new WanChai());
        map.add(new Tax());
        map.add(new Stanley());
        map.add(new Jail());
        map.add(new ShekO());
        map.add(new MongKok());
        map.add(new Chance(9));
        map.add(new TsingYi());
        map.add(new FreePark());
        map.add(new ShaTin());
        map.add(new Chance(13));
        map.add(new TuenMun());
        map.add(new TaiPo());
        map.add(jail);
        map.add(new SaiKung());
        map.add(new YuenLong());
        map.add(new Chance(19));
        map.add(new TaiO());
        for (int x = 0; x < numOfPlayer; x++) {
            System.out.println("Please input player name:   ");
            String name = sc.next();
            players.add(createNewPlayer(name));
        }
        pgb = new PrintGameBoard();
        pgb.printProcess(map, players);
        showCurrentGame();
    }

    public boolean checkPlayerNum(int numOfPlayer) {
        if (numOfPlayer >= 2 && numOfPlayer <= 6) {
            return true;
        } else {
            return false;
        }
    }

    public Player createNewPlayer(String name) {
        return new Player(name, 1500);
    }

    public int rollDice() {
        return ThreadLocalRandom.current().nextInt(1, 4 + 1);
    }

    public ArrayList<Player> getPlayer() {
        return players;
    }

    public ArrayList<Map> getMap() {
        return map;
    }

    public void changeUserName() {
        Player player = players.get(currentPlayer);
        System.out.println("Please input a new name: ");
        String newName = sc.next();
        player.setName(newName);
        System.out.println("Your name has been modified to: " + newName + "\n\n");
    }

    public void viewGameRules() {
        System.out.println(
                "\n\nThis is a monopoly game. Player can roll dice every round, each place has different functions and effect.\n\n"
                        + "The win objective of this game is either the most money after 100 rounds and there is only one player left with the most money.\n\n"
                        + "As the punishment in the game, there is a jail in the game. A player gets out of jail by:\n"
                        + "either throwing doubles on any of her next three turns (if she succeeds in doing his, she immediately moves forward by the number of spaces shown by her doubles throw)\n"
                        + "or paying a fine of HKD 150 before she rolls the dice on either of her next two turns.\n"
                        + "If the player does not throw doubles by her third turn, she must pay the HKD 150 fine. She then gets out of jail and immediately moves forward the number of spaces shown by her throw.\n\n"
                        + "Regarding the functions, player can buy property and if other player step on it, that player needs to pay rent.\n\n");
    }

    public void playGame() {
        boolean inJail = false;
        boolean outOfJail = false;
        dice1 = rollDice();
        dice2 = rollDice();
        Player player = players.get(currentPlayer);
        location = player.getLocation() + dice1 + dice2;
        if (jail.checkPrisoner(player.getName())) {
            inJail = outOfPrison(player);
            if (!inJail) {
                outOfJail = true;
            }
        } else {
            if (location > 20) {
                location -= 20;
                player.setMoney(player.getMoney() + 1500);
            }
        }
        if (!inJail) {
            player.setLocation(location);
            pgb.printProcess(map, players);
            System.out.println("You roll " + Integer.toString(dice1) + " and " + Integer.toString(dice2));
            if (outOfJail) {
                System.out.println("You get out of the jail!");
            }
            System.out.println();
            for (int x = 0; x < map.size(); x++) {
                if (location == map.get(x).getLocation()) {
                    switch (map.get(x).getType()) {
                    case "property":
                        p = (Property) map.get(x);
                        String owner = p.getOwner();
                        System.out.println(player.getName() + " Step on  " + p.getName());
                        if (owner != null) {
                            if (!owner.equals(player.getName())) {
                                if (checkEnough(player.getMoney(), p.getRent())) {
                                    player.setMoney(player.getMoney() - p.getRent());
                                    for (int i = 0; i < players.size(); i++) {
                                        if (p.getOwner().equals(players.get(i).getName())) {
                                            Player pOwner = players.get(i);
                                            pOwner.setMoney(pOwner.getMoney() + p.getRent());
                                        }
                                    }
                                    pgb.printProcess(map, players);
                                    System.out.println();
                                    System.out.println(player.getName() + " Step on  " + p.getName());
                                    System.out.println("You paid $" + p.getRent() + " to " + p.getOwner());
                                } else {
                                    loser(player);
                                }
                            }
                        } else {
                            if (checkInput("Buy property? ")) {
                                if (checkEnough(player.getMoney(), p.getPrice())) {
                                    p.setOwner(player.getName());
                                    player.setMoney(player.getMoney() - p.getPrice());
                                    pgb.printProcess(map, players);
                                    System.out.println();
                                    System.out.println(player.getName() + " buy " + p.getName());
                                } else {
                                    System.out.println("You do not have enough money!");
                                }
                            }
                        }
                        break;
                    case "Chance":
                        Chance c = (Chance) map.get(x);
                        int luckymoney = c.getChance();
                        if (checkEnough(player.getMoney(), luckymoney)) {
                            player.setMoney(player.getMoney() + luckymoney);
                            System.out.println(player.getName() + " step on  " + map.get(x).getType());
                            System.out.println("you get $ " + luckymoney);
                        } else {
                            loser(player);
                        }

                        break;
                    case "Tax":
                        Tax t = (Tax) map.get(x);
                        int tax = t.payTax(player.getMoney());
                        player.setMoney(player.getMoney() - tax);
                        System.out.println(player.getName() + " step on  " + map.get(x).getType());
                        System.out.println("You have deducted $" + tax);
                        break;
                    case "Go to Jail":
                        jail.setPrisoner(player.getName());
                        player.setLocation(6);
                        pgb.printProcess(map, players);
                        System.out.println(player.getName() + " step on  " + map.get(x).getType());
                        System.out.println("You Go to Jail");
                        break;
                    default:
                        System.out.println(player.getName() + " step on  " + map.get(x).getType());
                        System.out.println("nothing happen");
                    }
                }
            }
        }
        checkCurrentPlayer();
        System.out.println();
        System.out.println();
        if (round <= 100) {
            showCurrentGame();
        } else {
            gameOver();
        }
    }

    public void checkCurrentPlayer() {
        currentPlayer++;
        if (currentPlayer > players.size() - 1) {
            currentPlayer = 0;
            round++;
        }
        if (!players.get(currentPlayer).getPlayable()) {
            checkCurrentPlayer();
        }
    }

    public void loser(Player player) {
        for (int y = 0; y < map.size(); y++) {
            if (map.get(y).getType().equals("property")) {
                p = (Property) map.get(y);
                if (p.getOwner() != null) {
                    if (p.getOwner().equals(player.getName())) {
                        p.setOwner(null);
                    }
                }
            }
        }
        player.setMoney(0);
        player.setPlayable(false);
        pgb.printProcess(map, players);
        System.out.println(
                "\n\n" + player.getName() + " Lose the game because there was not enough money to pay the fine");
        numOfPlayer--;
        if (numOfPlayer == 1) {
            gameOver();
        }
    }

    public void gameOver() {
        ArrayList<String> winners = new ArrayList<String>();
        int maxMoney = 0;
        int money = 0;
        System.out.println("\n\n\n\n\n\n\n\n\n\nResult:\n");
        viewStatus(false, true);
        for (int x = 0; x < players.size(); x++) {
            money = Math.max(maxMoney, players.get(x).getMoney());
            if (money > maxMoney) {
                maxMoney = money;
                winners.clear();
            }
            if (money > 0 && money == players.get(x).getMoney()) {
                winners.add(players.get(x).getName());
            }
        }
        System.out.print("\n\nThe Winner is ");

        for (int z = 0; z < winners.size(); z++) {
            System.out.print(winners.get(z));
            if (winners.size() > 1 && z <= winners.size() - 2) {
                System.out.print(" and ");
            }
        }
        System.out.println(" !!  Congratulations!!\n\n\n");
        System.out.println("Game Over");
        System.exit(0);
    }

    public boolean outOfPrison(Player player) {
        boolean inJail;
        int time = jail.getTime(player.getName());
        inJail = true;
        if (time > 1) {
            if (checkInput("Pay 150 to leave?")) {
                if (checkEnough(player.getMoney(), 150)) {
                    inJail = false;
                    jail.removePrisoner(player.getName());
                    player.setMoney(player.getMoney() - 150);
                } else {
                    System.out.println("You do not have enough money!");
                }

            }
        }
        if (dice1 == dice2) {
            inJail = false;
            jail.removePrisoner(player.getName());
        } else if (time == 3) {
            inJail = false;
            jail.removePrisoner(player.getName());
            player.setMoney(player.getMoney() - 150);
        }
        if (inJail) {
            time += 1;
            jail.addTime(player.getName(), time);
            pgb.printProcess(map, players);
            System.out.println("You roll " + Integer.toString(dice1) + " and " + Integer.toString(dice2));
            System.out.println("you can not make it");
        }
        return inJail;
    }

    public boolean checkEnough(int pMoney, int number) {
        boolean enough = true;
        if (pMoney - Math.abs(number) < 0) {
            enough = false;
        }
        return enough;
    }

    public boolean checkInput(String question) {
        boolean correct = false;
        System.out.println(question + " Yes - y; No - n;");
        String choice = sc.next();
        if (choice.equals("y")) {
            correct = true;
        } else if (choice.equals("n")) {
            correct = false;
        } else {
            System.out.println("\nWrong command! please input again!\n");
            checkInput(question);
        }
        return correct;
    }

    public void viewStatus(boolean personal, boolean finish) {
        int total;
        int first;
        String isInJail;
        if (personal) {
            first = currentPlayer;
            total = currentPlayer + 1;
        } else {
            first = 0;
            total = players.size();
        }
        System.out.format("%-15s%-20s%-10s%-10s%n", "player name", "location", "amount", "is in jail");
        for (int x = first; x < total; x++) {
            if (jail.checkPrisoner(players.get(x).getName())) {
                isInJail = "Yes; Ronund " + jail.getTime(players.get(x).getName());
            } else {
                isInJail = "No";
            }
            int location = players.get(x).getLocation();
            System.out.format("%-15s%-20s%-10s%-10s%n", players.get(x).getName(),
                    location + ". " + getLocationName(location), players.get(x).getMoney(), isInJail);
            System.out.println();
        }
        String answer = "";
        if (!finish) {
            if (checkInput("view properties?")) {
                answer = "y";
            }
        } else {
            answer = "y";
        }
        if (answer.equals("y")) {
            System.out.println("\n\nProperty that has been purchased:\n");
            for (int x = first; x < total; x++) {
                System.out.print(players.get(x).getName() + ":   ");
                for (int y = 0; y < map.size(); y++) {
                    if (map.get(y).getType().equals("property")) {
                        p = (Property) map.get(y);
                        if (p.getOwner() != null) {
                            if (p.getOwner().equals(players.get(x).getName())) {
                                System.out.print(p.getName() + "   ");
                            }
                        }
                    }
                }
                System.out.println();
            }
        }
        System.out.println();
    }

    public String getLocationName(int location) {
        String locationName = "";
        Map m = map.get(location - 1);
        if (m.getType().equals("property")) {
            p = (Property) m;
            locationName = p.getName();
        } else {
            locationName = m.getType();
        }
        return locationName;
    }

    public void showCurrentGame() {
        int location = players.get(currentPlayer).getLocation();
        System.out.println("Now is " + players.get(currentPlayer).getName() + " turn");
        System.out.println("Your location is: " + location + ". " + getLocationName(location));
        System.out.println();
        System.out.println("Round " + round);
    }

    public void saveGame() {
        FileOutputStream fileOut;
        ObjectOutputStream out;
        try {
            fileOut = new FileOutputStream("./monopoly/src/main/java/monopoly/save/map" + round + ".ser");
            out = new ObjectOutputStream(fileOut);
            out.writeObject(map);
            fileOut = new FileOutputStream("./monopoly/src/main/java/monopoly/save/players" + round + ".ser");
            out = new ObjectOutputStream(fileOut);
            out.writeObject(players);
            fileOut = new FileOutputStream("./monopoly/src/main/java/monopoly/save/jail" + round + ".ser");
            out = new ObjectOutputStream(fileOut);
            out.writeObject(jail);
            fileOut = new FileOutputStream("./monopoly/src/main/java/monopoly/save/currentPlayer0000" + round + ".ser");
            out = new ObjectOutputStream(fileOut);
            out.writeObject(currentPlayer);
            fileOut = new FileOutputStream("./monopoly/src/main/java/monopoly/save/round00000000" + round + ".ser");
            out = new ObjectOutputStream(fileOut);
            out.writeObject(round);
            out.close();
            fileOut.close();
            System.out.println("Serialized data is saved");
        } catch (IOException i) {
            i.printStackTrace();
        }
        save.add(round);
    }

    public void loadGame() {
        boolean checker = false;
        int savedRound = 0;
        while (!checker) {
            System.out.print("Please select which round you want to load: ");
            for (int i = 0; i < save.size(); i++) {
                System.out.print(save.get(i) + "; ");
            }
            try {
                savedRound = sc.nextInt();
                for (int i = 0; i < save.size(); i++) {
                    if (savedRound == save.get(i)) {
                        checker = true;
                    }
                }
            } catch (Exception e) {
                System.out.println("Please enter a correct Number!\n\n");
                sc.nextLine();
            }
            if(!checker){
                System.out.println("Please enter a correct Round!\n\n");
            }
        }
        try {
            FileInputStream fileIn;
            ObjectInputStream in;
            fileIn = new FileInputStream("./monopoly/src/main/java/monopoly/save/map" + savedRound + ".ser");
            in = new ObjectInputStream(fileIn);
            map = (ArrayList<Map>) in.readObject();
            fileIn = new FileInputStream("./monopoly/src/main/java/monopoly/save/players" + savedRound + ".ser");
            in = new ObjectInputStream(fileIn);
            players = (ArrayList<Player>) in.readObject();
            fileIn = new FileInputStream("./monopoly/src/main/java/monopoly/save/jail" + savedRound + ".ser");
            in = new ObjectInputStream(fileIn);
            jail = (GoToJail) in.readObject();
            fileIn = new FileInputStream(
                    "./monopoly/src/main/java/monopoly/save/currentPlayer0000" + savedRound + ".ser");
            in = new ObjectInputStream(fileIn);
            currentPlayer = (Integer) in.readObject();
            fileIn = new FileInputStream("./monopoly/src/main/java/monopoly/save/round00000000" + savedRound + ".ser");
            in = new ObjectInputStream(fileIn);
            round = (Integer) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException i) {
            i.printStackTrace();
            return;
        } catch (ClassNotFoundException c) {
            c.printStackTrace();
            return;
        }
        pgb.printProcess(map, players);
        showCurrentGame();
    }

}