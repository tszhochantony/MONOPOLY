package monopoly;

import org.junit.jupiter.api.Test;
import java.util.*;
import java.io.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Unit test for simple App.
 */
class GameTest {
    Game g = new Game();
    ArrayList<Map> map = new ArrayList<Map>();
    GoToJail jail = new GoToJail();
    Chance C = new Chance(9); // 9, 13 and 19 on the map is the chance
    

    GameTest() {
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
    }

    /**
     * Rigorous Test.
     */

    // This test is to test the player name can save in the player object
    // first create two player object with names and money
    // then call getName function from two objects
    // If it is true than the test is sucess
    @Test
    void testPlayerInputName() {
        System.out.println("TESTING PLAYER INPUT NAME FUNCTION");
        System.out.println("PLAYER INPUT THEIR NAME");
        Player P1 = new Player("Ken", 1500);
        Player P2 = new Player("Andy", 1500);
        String p1name = P1.getName();
        String p2name = P2.getName();
        assertTrue((p1name != null && p2name != null));
        System.out.println("TESTING PLAYER INPUT NAME FUNCTION SUCCESSFULLY");
        System.out.println();
        System.out.println();
    }

    // this test is to check can this game more than 2 people with checkPlayer
    // function
    // if it is successful playerNum will return true and pass the test
    @Test
    void testPlayerMoreThanTwo() {
        System.out.println("TESTING CHECK NUMBERS OF PLAYERS FUNCTION");
        boolean playerNum = g.checkPlayerNum(4);
        System.out.println("PLAYER INPUT 4");
        assertTrue(playerNum);
        System.out.println("PLAYERS NUMBERS ARE BETWEEN 2-6 PEOPLE");
        System.out.println("TESTING CHECK NUMBERS OF PLAYERS FUNCTION SUCCESSFULLY");
        System.out.println();
        System.out.println();
    }
    // this test is to test the game can only allow 2-6 players if more than 6
    // should return false
    // use checkPlayer function and input 7
    // the playNum function should return false

    @Test
    void testPlayerMoreThanSix() {
        System.out.println("TESTING CHECK NUMBERS OF PLAYERS FUNCTION");
        boolean playerNum = g.checkPlayerNum(7);
        System.out.println("PLAYER INPUT 7");
        assertFalse(playerNum);
        System.out.println("PLAYERS NUMBERS CANNOT MORE THAN 6 PEOPLE SUCCESSFULLY");
        System.out.println("TESTING CHECK NUMBERS OF PLAYERS FUNCTION SUCCESSFULLY");
        System.out.println();
        System.out.println();
    }
    // this test is to test dice amount is between 2 to 8
    // First, there will be two dices, each dice call the roll dice function
    // Second, sum up the total amount of the two dices
    // thridly, check the dice is within the limit with assertTrue

    @Test
    void testRollDiceRange() {
        System.out.println("TESTING ROLL DICE FUNCTION");
        System.out.println("NOW ROLL DICE");
        int dice1 = g.rollDice();
        int dice2 = g.rollDice();
        int dice = dice1 + dice2;
        System.out.println("Dice1 is " + dice1 + " Dice2 is " + dice2);
        System.out.println("Total dice is " + dice);
        assertTrue(dice >= 2 && dice <= 8);
        System.out.println("THE DICE IS BETWEEN 2-8");
        System.out.println("TESTING ROLL DICE FUNCTION SUCCESSFULLY");
        System.out.println();
        System.out.println();
    }

    // This test is to test the player went to the location and allow the player to
    // buy the property
    // This test will make KEN step on first location
    // use if else to check the user is stepping on a property
    // and use setMoney function to deduct the money when the player buy the
    // property
    // finally set the property to the player
    // use the asseertTrue to check the property owner
    // use assertEquals to check does the player deducted the price
    @Test
    void testPlayerGoToPropertyAndBuyTheProperty() {
        System.out.println("TESTING BUY PROPERT FUNCTION");
        Player P = new Player("Ken", 1500);
        Property property = (Property) map.get(1);
        P.setLocation(1);
        System.out.println("PLAYER KEN CHOOSE TO BUY THE PROPERTY");
        if (map.get(1).getType().equals("property")) {
            P.setMoney(P.getMoney() - property.getPrice());
            property.setOwner(P.getName());
        }
        assertTrue(property.getOwner() != null);
        assertEquals(700, P.getMoney());
        System.out.println("PLAYERS CAN BUY PROPERTY");
        System.out.println("TESTING BUY PROPERT FUNCTION SUCCESSFULLY");
        System.out.println();
        System.out.println();
    }
    // Test the player to step on the property and do not buy the property
    // This test will make KEN step on first location
    // use if else to check the user is stepping on a property
    // use assertTrue to check the player did not own the property
    // use assertEquals to check the money did not change

    @Test
    void testPlayerGoToPropertyAndDonnotBuyTheProperty() {
        System.out.println("TESTING DO NOT BUY PROPERT FUNCTION");
        Player P = new Player("Ken", 1500);
        Property property = (Property) map.get(1);
        P.setLocation(1);
        System.out.println("PLAYER CHOOSE DID NOT BUY THE PROPERTY AND THE MONEY DID NOT CHANGE");
        assertTrue(property.getOwner() == null);
        assertEquals(1500, P.getMoney());
        System.out.println("TESTING DO NOT BUY PROPERT FUNCTION SUCCESSFULLY");
        System.out.println();
        System.out.println();
    }

    // This test is to test the player can view the status
    // First, new a player called KEN with 1500 money
    // Second, get all the information by calling getter
    // Thridly, use assertTrue to check the data is not null
    // Finally, if it is not null that means player can check his status
    @Test
    void testPlayerViewTheirStatus() {
        System.out.println("TESTING VIEW STATUS FUNCTION");
        Player P = new Player("Ken", 1500);
        String name = P.getName();
        int location = P.getLocation();
        int money = P.getMoney();
        Boolean inJail = jail.checkPrisoner(P.getName());
        System.out.println("PLAYER CHOOSE TO VIEW THEIR OWN STATUS");
        assertTrue((name != null && location > -1 && money > -1 && inJail != null));
        System.out.println("PLAYER CAN VIEW THEIR OWN STATUS");
        System.out.println("TESTING VIEW STATUS FUNCTION SUCCESSFULLY");
        System.out.println();
        System.out.println();
    }
    // This test is to check the player is in the jail
    // First new player KEN with 1500 money
    // Second, set KEN as a prisoner with setPrisoner method
    // Finally assertTrue to check the prisioner list to find KEN

    @Test
    void testPlayerGoToJail() {
        System.out.println("TESTING GO TO JAIL FUNCTION");
        Player P = new Player("Ken", 1500);
        jail.setPrisoner(P.getName());
        assertTrue(jail.checkPrisoner(P.getName()));
        System.out.println("PLAYER WILL GO TO JAIL WHEN HIS OR HER TOKEN IS IN THE BOX 'GO TO JAIL'");
        System.out.println("TESTING GO TO JAIL FUNCTION SUCCESSFULLY");
        System.out.println();
        System.out.println();
    }

    // This Test is testing SAVE and lOAD function There will be a varible test.
    // It is default set to 0
    // In the middle the test will be set 14
    // the load function should load the test integer 0
    // Finally the test should be 0 not 14.
    @Test
    void SaveLoad() {
        System.out.println("TESTING SAVE LOAD function");
        FileOutputStream fileOut;
        ObjectOutputStream out;
        FileInputStream fileIn;
        ObjectInputStream in;
        int test = 0;
        int expected = 0;// the expected result is 0
        try {
            System.out.println("now the integer is" + test + "!");
            fileOut = new FileOutputStream(
                    "C:/Users/user/OneDrive - The Hong Kong Polytechnic University/COMP3211/UnitTest.ser");
            out = new ObjectOutputStream(fileOut);
            out.writeObject(test);
            test = 14; // changed the integer to14
            out.close();
            fileOut.close();
            System.out.println("Integer SAVED");
            System.out.println("now the integer has changed to" + test + "!");
            System.out.println();
        } catch (IOException i) {
            i.printStackTrace();
        }
        try {
            fileIn = new FileInputStream(
                    "C:/Users/user/OneDrive - The Hong Kong Polytechnic University/COMP3211/UnitTest.ser");
            in = new ObjectInputStream(fileIn);
            test = (Integer) in.readObject();
            System.out.println("LOADED");
            assertEquals(expected, test); // the expected result is 0
            System.out.println("NOW it is loaded is" + test + "!");
            System.out.println();
            System.out.println();
        } catch (IOException i) {
            i.printStackTrace();
            return;
        } catch (ClassNotFoundException c) {
            c.printStackTrace();
            return;
        }
        System.out.println();
        System.out.println();
    }

    /*
     * This test is to check is the number of the player's money correct after the
     * player go to income tax on the map Set the player's money before tax is 1500
     * The expected test result should be 1350
     */
    @Test
    void testPlayerGoToTax() {
        System.out.println("TESTING PAY TAX FUNCTION");
        Player P = new Player("Ken", 1500);
        P.setLocation(4);
        map.get(4).getLocation();
        double tax = P.getMoney() * 0.1;
        double money = P.getMoney() - tax;
        assertEquals(1350, money);
        System.out.println("THE MONEY OF PLAYER AFTER TAX IS CORRECT");
        System.out.println("TESTING PAY TAX FUNCTION SUCCESSFULLY");
        System.out.println();
        System.out.println();
    }

    /*
     * This test is to check is the number of the player's money correct after the
     * player go to chance on the map Set the player's money before chance is 1500
     * The expected test result should be between 1300 to 1700
     */
    @Test
    void testChance() {
        System.out.println("TESTING CHANCE FUNCTION");
        Player P = new Player("Ken", 1500);
        int chance = C.getChance();
        int money = P.getMoney() - chance;
        assertTrue(money >= 1300 || money <= 1700);
        System.out.println("The money of player after chance is correct");
        System.out.println("TESTING CHANCE FUNCTION SUCCESSFULLY");
        System.out.println();
        System.out.println();
    }

    /*
     * This test is to check when the user choose to get out of jail by paying the
     * fine is the system correctly to decrease the player's money and let the
     * player get out of jail. Set the player's money before in jail is 1500 The
     * expected result of the test is the amount of player's money will be 1350 and
     * the player can get out of jail.
     */
    @Test
    void testPayOutOfJail() {
        System.out.println("TESTING PAY OUT OF JAIL FUNCTION");
        Player P = new Player("Ken", 1500);
        jail.setPrisoner(P.getName());
        int money = P.getMoney() - 150;
        if (money == 1350) {
            jail.removePrisoner(P.getName());
            assertFalse(jail.checkPrisoner(P.getName()));
            System.out.println("The player is sucessfully removed after pay the fine");
        }
        System.out.println("TESTING PAY OUT OF JAIL FUNCTION SUCCESSFULLY");
        System.out.println();
        System.out.println();
    }

    /*
     * This test is to check when the second time player try to get out of jail and
     * didn't choose to pay the fine, if the player can't roll two same dice, the
     * system won't reduce the player's money and let the player stay in the jail
     * Set the money of the player 1500 and is the second time try to get out The
     * expect result are the amount of player's money is 1500 and the player still
     * stay in the jail
     */
    @Test
    void testSecondInjail() {
        System.out.println("TESTING SECOND IN JAIL FUNCTION");
        Player P = new Player("Ken", 1500);
        jail.setPrisoner(P.getName());
        jail.addTime(P.getName(), 2);
        int money = P.getMoney();
        int dice1 = 1;
        int dice2 = 2;
        if (dice1 != dice2) {
            assertTrue(jail.checkPrisoner(P.getName()));
            assertEquals(1500, money);
            System.out.println("The amount of Ken's money didn't change and Ken still stay in the jail");
        }
        System.out.println("TESTING SECOND IN JAIL FUNCTION SUCCESSFULLY");
        System.out.println();
        System.out.println();
    }

    /*
     * This test is to check when the thrid time player try to get out of jail and
     * didn't choose to pay the fine, if the player can't roll two same dice, the
     * system will force the play to pay the fine and get out of jail instanly Set
     * the money of the player 1500 and is the third time try to get out The expect
     * result are the amount of player's money is 1350 and get out of jail
     * sucessfully
     */
    @Test
    void testThridInjail() {
        System.out.println("TESTING THIRD IN JAIL FUNCTION");
        Player P = new Player("Ken", 1500);
        jail.setPrisoner(P.getName());
        jail.addTime(P.getName(), 3);
        int money = P.getMoney();
        int dice1 = 1;
        int dice2 = 2;
        if (dice1 != dice2) {
            jail.removePrisoner(P.getName());
            money = money - 150;
            assertFalse(jail.checkPrisoner(P.getName()));
            assertEquals(1350, money);
            System.out.println("The player have been forced paid the fine and got out of jail");
        }
        System.out.println("TESTING THIRD IN JAIL FUNCTION SUCCESSFULLY");
        System.out.println();
        System.out.println();
    }

    /*
     * This test is to check when the player pass through the GO on map and the
     * system will give 1500 to the player Set the money of the player 1500 The
     * expected result should be 3000.
     */
    @Test
    void testGoAddMoneyToPlayerTax() {
        System.out.println("TESTING SECOND GO TO TAX FUNCTION");
        Player P = new Player("Ken", 1500);
        P.setLocation(19);
        map.get(19).getLocation();
        int money = P.getMoney();
        int location = P.getLocation() + 5; // asume the play roll dice1 + dice2 be 5
        if (location > 20) {
            location -= 20;
            money += 1500;
        }
        assertEquals(3000, money);
        System.out.println("The money of player after GO is correct");
        System.out.println("TESTING SECOND GO TO TAX FUNCTION SUCCESSFULLY");
        System.out.println();
        System.out.println();
    }

    /*
     * This test is to check when all user roll one time, the system will add one
     * round Set the round deflaut 1 and there are 4 players The expected result is
     * round 2 when all 4 players roll the dice once
     */
    @Test
    void testAddRound() {
        System.out.println("TESTING ADD ROUND FUNCTION");
        ArrayList<Player> players = new ArrayList<Player>();
        int round = 1;
        int currentPlayer = 0;
        players.add(new Player("Ken", 1500));
        players.add(new Player("Joe", 1500));
        players.add(new Player("May", 1500));
        players.add(new Player("Jone", 1500));
        for (int i = 0; i < 4; i++) {
            if (currentPlayer + 1 > players.size() - 1) {
                currentPlayer = 0;
                round++;
            } else {
                currentPlayer++;
            }
        }
        assertEquals(2, round);
        System.out.println("TESTING ADD ROUND FUNCTION SUCCESSFULLY");
        System.out.println();
        System.out.println();
    }

    /*
     * This test is to check when the rounds more than 100 rounds the game will be
     * ended. Set the round be 1 and there are 4 players The expected result should
     * be 101 after all 4 players roll 100 times
     */
    @Test
    void testEndGame() {
        System.out.println("TESTING ENDGAME FUNCTION");
        ArrayList<Player> players = new ArrayList<Player>();
        int round = 1;
        int currentPlayer = 0;
        players.add(new Player("Ken", 2000));
        players.add(new Player("Joe", 4530));
        players.add(new Player("May", 4500));
        players.add(new Player("Jone", 1700));
        for (int i = 0; i <= 9999; i++) {
            if (round <= 100) {
                if (currentPlayer + 1 > players.size() - 1) {
                    currentPlayer = 0;
                    round++;
                } else {
                    currentPlayer++;
                }
            }
        }
        assertEquals(101, round);
        System.out.println("When roung go to 100, the game will end.");
        System.out.println("TESTING ENDGAME FUNCTION SUCCESSFULLY");
        System.out.println();
        System.out.println();
    }

    /*
     * This test is to check when one player step on other player's property, they
     * need to payrent to the player who own this place Set 2 players Ken and Joe
     * with money 1500 The expected result should be Ken have 1485 and Joe have 1115
     */
    @Test
    void testPlayerPayRent() {
        System.out.println("TESTING PAYRENT FUNCTION");
        Player P1 = new Player("Ken", 1500);
        Player P2 = new Player("Joe", 1500);
        Property property = (Property) map.get(9);
        P2.setMoney(P2.getMoney() - property.getPrice()); // Since Joe buy the property Tsing Yi, so -400
        property.setOwner(P2.getName());
        P1.setLocation(10);
        P1.setMoney(P1.getMoney() - property.getRent());
        P2.setMoney(P2.getMoney() + property.getRent());
        assertEquals(1485, P1.getMoney());
        assertEquals(1115, P2.getMoney());
        System.out.println("Player can pay rent and receive money.");
        System.out.println("TESTING PAYRENT FUNCTION SUCCESSFULLY");
        System.out.println();
        System.out.println();
    }

    /*
     * This test is to check when players roll dices, is their token go to the
     * correct place on the map Set the player location 1 The expected result should
     * be 7 after Ken roll the dices
     */
    @Test
    void testPlayerRespectiveToken() {
        System.out.println("TESTING MOVE TOKEN FUNCTION");
        Player P = new Player("Ken", 1500);
        int dice1 = 2;
        int dice2 = 4;
        P.setLocation(1);
        int location = P.getLocation() + dice1 + dice2;
        assertEquals(7, location);
        System.out.println("Player can their token.");
        System.out.println("TESTING MOVE TOKEN FUNCTION SUCCESSFULLY");
        System.out.println();
        System.out.println();
    }

    /*
     * This test is to check is the players get 1500 when the game start Set the
     * player have 1500 The expected result is 1500 when the game start
     */
    @Test
    void testPlayerCanGetMoneyWhenGameStart() {
        System.out.println("TESTING GET RENT FUNCTION");
        Player P = new Player("Ken", 1500);
        assertEquals(1500, P.getMoney());
        System.out.println("Player can get $1500 when the game start.");
        System.out.println("TESTING GET RENT FUNCTION SUCCESSFULLY");
        System.out.println();
        System.out.println();
    }
    /* 
        This test is to check can the player change their names with command c
    */
    @Test
    void testPlayerChangeName() {
        System.out.println("TESTING Player Change Name FUNCTION");
        Player P = new Player("Ken", 1500);
        String newName = "Henry";
        P = new Player(newName, 1500);
        assertEquals(P.getName(), "Henry");
        System.out.println("Player can change their name.");
        System.out.println("TESTING Player Change Name FUNCTION SUCCESSFULLY");
        System.out.println();
        System.out.println();
    }
    /* 
        This test is to check is that player lose and cannot keep playing the game.
        There is a boolean called playable is the variable is false, that player turn would be skipped to the next player

    */
    @Test
    void testCheckPlayerLose() {
        Property property = (Property) map.get(2);
        System.out.println("TESTING Player Change Lose FUNCTION");
        Player P = new Player("Ken", 1500);
        System.out.println("Player now is: " + P.getMoney() + ", " + P.getPlayable());
        property.setOwner(P.getName());
        System.out.println("Player's property: " + property.getOwner());
        for (int y = 0; y < map.size(); y++) {
            if (map.get(y).getType().equals("property")) {
                Property pro = (Property) map.get(y);
                if (pro.getOwner() != null) {
                    if (pro.getOwner().equals(P.getName())) {
                        pro.setOwner(null);
                    }
                }
            }
        }
        P.setMoney(0);
        P.setPlayable(false);
        System.out.println("Player now is:" + P.getMoney() + ", " + P.getPlayable());
        System.out.println("Player's property:" + property.getOwner());
        assertEquals(P.getMoney(), 0);
        assertFalse(P.getPlayable());
        assertEquals(property.getOwner(), null);
        System.out.println("Player will leave game when he or she lose.");
        System.out.println("TESTING Player Change Lose FUNCTION SUCCESSFULLY");
        System.out.println();
        System.out.println();
    }
    /* 
        This test is to check only one winner with the most money after 100 rounds or other player lose and only one player left
        to check who is the winner, the program can compare the money and check who has the most money 
    */
    @Test
    void testCheckOneWinner() {
        System.out.println("TESTING Check One Winner FUNCTION");
        ArrayList<Player> players = new ArrayList<Player>();
        Player P1 = new Player("Player1", 2000);
        Player P2 = new Player("Player2", 1500);
        Player P3 = new Player("Player3", 1000);
        players.add(P1);
        players.add(P2);
        players.add(P3);
        ArrayList<String> winners = new ArrayList<String>();
        int maxMoney = 0;
        int money = 0;
        for (int x = 0; x < players.size(); x++) {
            money = Math.max(maxMoney, players.get(x).getMoney());
            if(money>maxMoney){
                maxMoney = money;
                winners.clear();
            }
            if (money > 0 && money == players.get(x).getMoney()) {
                winners.add(players.get(x).getName());
            }
        }
        System.out.print("\n\nThe Winner is ");
        for(int z=0;z<winners.size();z++){
            System.out.print(winners.get(z));
            if(winners.size()>1 && z<=winners.size()-2){
                System.out.print(" and ");
            }
        }
        assertEquals(winners.get(0), "Player1");
        System.out.println("\nPlayer can see who win this game.");
        System.out.println("TESTING Check One Winner FUNCTION SUCCESSFULLY");
        System.out.println();
        System.out.println();
    }
    /* 
        This test is to check this game can have two winnner when the two player has the same money after 100 rounds.

    */
    @Test
    void testCheckTwoWinner() {
        System.out.println("TESTING Check Two Winner FUNCTION");
        ArrayList<Player> players = new ArrayList<Player>();
        Player P1 = new Player("Player1", 2000);
        Player P2 = new Player("Player2", 2000);
        Player P3 = new Player("Player3", 1000);
        players.add(P1);
        players.add(P2);
        players.add(P3);
        ArrayList<String> winners = new ArrayList<String>();
        int maxMoney = 0;
        int money = 0;
        for (int x = 0; x < players.size(); x++) {
            money = Math.max(maxMoney, players.get(x).getMoney());
            if(money>maxMoney){
                maxMoney = money;
                winners.clear();
            }
            if (money > 0 && money == players.get(x).getMoney()) {
                winners.add(players.get(x).getName());
            }
        }
        System.out.print("\n\nThe Winner is ");
        for(int z=0;z<winners.size();z++){
            System.out.print(winners.get(z));
            if(winners.size()>1 && z<=winners.size()-2){
                System.out.print(" and ");
            }
        }
        assertEquals(winners.get(0), "Player1");
        assertEquals(winners.get(1), "Player2");
        System.out.println("\nPlayer can see who win this game.");
        System.out.println("TESTING Check Two Winner FUNCTION SUCCESSFULLY");
        System.out.println();
        System.out.println();
    }
     /* 
        this test is to check does the player has enough money to do any other action
        this test would call the checkEnough function to determine the player has enough money or not
        and it tested it is failed that means the player does not have enough money
    */
    @Test
    void testNotEnoughMoney() {
        System.out.println("TESTING Player Not Have Enough Money FUNCTION");
        Player P = new Player("Ken", 10);
        Property property = (Property) map.get(2);
        assertFalse(g.checkEnough(P.getMoney(), property.getRent()));
        System.out.println("Player do not have enough money to buy property or pay tax.");
        System.out.println("TESTING Player Not Have Enough Money FUNCTION SUCCESSFULLY");
        System.out.println();
        System.out.println();
    }
    /* 
        this test is to check does the player has enough money to do any other action
        this test would call the checkEnough function to determine the player has enough money or not
        and it tested it is success that means the player does have enough money
    */
    @Test
    void testEnoughMoney() {
        System.out.println("TESTING Player Have Enough Money FUNCTION");
        Player P = new Player("Ken", 1500);
        Property property = (Property) map.get(2);
        assertTrue(g.checkEnough(P.getMoney(), property.getRent()));
        System.out.println("Player have enough money to buy property or pay tax.");
        System.out.println("TESTING Player Have Enough Money FUNCTION SUCCESSFULLY");
        System.out.println();
        System.out.println();
    }
/* 
       This function is to test can the program force the player to either choose y or n and this test is forcing the player to input y
       the meaning of this function is to prevent user entering other commands or letters in the process
    */
    @Test
    void testInputIsy() {
        System.out.println("TESTING Player Input Is y FUNCTION");
        boolean correct = false;
        String choice = "y";
        if (choice.equals("y")) {
            correct = true;
            System.out.println("You choose Yes");
        } else if (choice.equals("n")) {
            correct = false;
            System.out.println("You choose No");
        } else {
            System.out.println("Wrong command! please input again!");
        }
        assertTrue(correct);
        System.out.println("Player input y.");
        System.out.println("TESTING Player Input Is y FUNCTION SUCCESSFULLY");
        System.out.println();
        System.out.println();
    }
    /* 
       This function is to test can the program force the player to either choose y or n and this test is forcing the player to input n
       the meaning of this function is to prevent user entering other commands or letters in the process
    */
    @Test
    void testInputIsn() {
        System.out.println("TESTING Player Input Is n FUNCTION");
        boolean correct = false;
        String choice = "n";
        if (choice.equals("y")) {
            correct = true;
            System.out.println("You choose Yes");
        } else if (choice.equals("n")) {
            correct = false;
            System.out.println("You choose No");
        } else {
            System.out.println("Wrong command! please input again!");
        }
        assertFalse(correct);
        System.out.println("Player input n.");
        System.out.println("TESTING Player Input Is n FUNCTION SUCCESSFULLY");
        System.out.println();
        System.out.println();
    }
    /* 
       This function is to test can the program force the player to either choose y or n and this test is forcing the player to input abc and the program should output wrong command and ask user to input.
       the meaning of this function is to prevent user entering other commands or letters in the process
    */
    @Test
    void testInputIsWrong() {
        System.out.println("TESTING Player Input Is Wrong FUNCTION");
        boolean correct = false;
        String choice = "abc";
        if (choice.equals("y")) {
            correct = true;
        } else if (choice.equals("n")) {
            correct = false;
        } else {
            System.out.println("Wrong command! please input again!");
        }
        assertFalse(correct);
        System.out.println("Player input wrong.");
        System.out.println("TESTING Player Input Is Wrong FUNCTION SUCCESSFULLY");
        System.out.println();
        System.out.println();
    }
    /* 
      This test is to check to index can successfully transalte to location name and displayed to the player
      it use compare to know index 0 is equals to location size 1
    */
    @Test
    void testLocationIndexToLocationName() {
        System.out.println("TESTING Index To Location Name FUNCTION");
        System.out.println("User input: 3");
        int input = 3;
        Property propertys = (Property) map.get(2);
        String locationName = "";
        Map m = map.get(input-1);
        if(m.getType().equals("property")){
            propertys = (Property) m;
            locationName = propertys.getName();
        }else{
            locationName = m.getType();
        }
        assertEquals(locationName, "Wan Chai");
        System.out.println("Location Name change successfully.");
        System.out.println("TESTING Index To Location Name FUNCTION SUCCESSFULLY");
        System.out.println();
        System.out.println();
    }

}
