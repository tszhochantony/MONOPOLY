package monopoly;

import java.util.*;

public class PrintGameBoard {
    String line1 = "+------------------------";
    String line2 = "+------------------------+";
    String line3 = "|                        ";
    String line4 = "                                 ";
    int index = 0;
    // int playerIndex = 0;
    ArrayList<String> printedPlayer = new ArrayList<String>();
    int enough = 100;
    ArrayList<Map> map = new ArrayList<Map>();
    ArrayList<Player> players = new ArrayList<Player>();
    int[] list = new int[] { 11, 12, 13, 14, 15, 16, 10, 17, 9, 18, 8, 19, 7, 20, 6, 5, 4, 3, 2, 1 };

    public void printProcess(ArrayList<Map> map, ArrayList<Player> players) {
        this.players = players;
        this.map = map;
        printedPlayer = new ArrayList<String>();
        printHorizontalField(1);
        printVerticalField();
        printHorizontalField(14);
    }

    public void printHorizontalField(int number) {
        for (int i = 0; i < 5; i++) {
            System.out.format(line1);
        }
        System.out.format(line2 + "%n");
        for (int x = 0; x < 8; x++) {
            if (number == 1) {
                index = 0;
            } else {
                index = 14;
            }
            for (int i = 0; i < 5; i++) {
                printContent(x);
            }
            printContent(x);
            System.out.format("|%n");
        }
        for (int i = 0; i < 5; i++) {
            System.out.format(line1);
        }
        System.out.format(line2 + "%n");
    }

    public void printVerticalField() {
        index = 6;
        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 8; x++) {
                if (x > 0) {
                    index -= 2;
                }
                printContent(x);
                System.out.format("|");
                for (int i = 0; i < 3; i++) {
                    System.out.format(line4);
                }
                printContent(x);
                System.out.format("|%n");
            }
            if (y < 3) {
                System.out.format(line2);
                for (int i = 0; i < 3; i++) {
                    System.out.format(line4);
                }
                System.out.format(line2 + "%n");
            }
        }
    }

    public void printContent(int x) {
        switch (x) {
        case 0:
            printProperty(index, "name");
            index++;
            break;
        case 1:
            printProperty(index, "rent");
            index++;
            break;
        case 2:
            printOwner(index);
            index++;
            break;
        default:
            printPlayer();
            index++;
        }
    }

    public void printProperty(int index, String type) {
        String content;
        if (list[index] == 1 || list[index] == 4 || list[index] == 6 || list[index] == 9 || list[index] == 11
                || list[index] == 13 || list[index] == 16 || list[index] == 19) {
            if (type.equals("name")) {
                Map m = map.get(list[index] - 1);
                content = m.getLocation() + ". " + m.getType();
            } else {
                content = "";
            }
        } else {
            Property p = (Property) map.get(list[index] - 1);
            if (type.equals("name")) {
                content = p.getLocation() + ". " + p.getName();
            } else {
                content = Integer.toString(p.getPrice());
            }
        }
        int length = (line3.length() - content.length() - 1) / 2;
        String newLine = "|";
        for (int z = 0; z < length; z++) {
            newLine += " ";
        }
        newLine += content;
        while (newLine.length() < line3.length()) {
            newLine += " ";
        }
        System.out.format(newLine);
    }

    public void printOwner(int index) {
        String owner = "";
        Map m;
        String type;
        for (int i = 0; i < map.size(); i++) {
            m = map.get(i);
            type = m.getType();
            if (type.equals("property")) {
                Property p = (Property) map.get(i);
                if (p.getLocation() == list[index]) {
                    owner = p.getOwner();
                    if (owner != null) {
                        owner = "Owner: " + p.getOwner();
                    } else {
                        owner = "No owner";
                    }
                }
            }
        }
        int length = (line3.length() - owner.length() - 1) / 2;
        String newLine = "|";
        for (int z = 0; z < length; z++) {
            newLine += " ";
        }
        newLine += owner;
        while (newLine.length() < line3.length()) {
            newLine += " ";
        }
        System.out.format(newLine);
    }

    public void printPlayer() {
        String player = "";
        for (int i = 0; i < players.size(); i++) {
            int location = players.get(i).getLocation();
            if (list[index] == location) {
                if (players.get(i).getPlayable()) {
                    player = players.get(i).getName();
                }
                for (int x = 0; x < printedPlayer.size(); x++) {
                    if (player.equals(printedPlayer.get(x))) {
                        player = "";
                    }
                }
                if (player != "") {
                    printedPlayer.add(player);
                    break;
                }
            }
        }
        int length = (line3.length() - player.length() - 1) / 2;
        String newLine = "|";
        for (int z = 0; z < length; z++) {
            newLine += " ";
        }
        newLine += player;
        while (newLine.length() < line3.length()) {
            newLine += " ";
        }
        System.out.format(newLine);
    }

}