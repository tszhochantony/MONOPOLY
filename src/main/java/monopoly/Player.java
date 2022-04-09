package monopoly;
public class Player implements java.io.Serializable{
    private String name;
    private int money;
    private int location;
    private boolean playable;

    public Player(String name,int money){
        this.name  = name;
        this.money = money;
        location = 1;
        playable = true;
    }

    public String getName(){return name;}
    public int getMoney(){return money;}
    public int getLocation(){return location;}
    public Boolean getPlayable(){return playable;}
    public void setName(String name){this.name = name;}
    public void setMoney(int money){this.money = money;}
    public void setLocation(int location){this.location = location;}
    public void setPlayable(boolean playable){this.playable = playable;}
}
