package monopoly;
public class Jail extends Map{
    public Jail(){
        super.location = 6;
        super.type = "Jail/visit";
    }
    public int getLocation(){
        return location;
    }
    public String getType(){
        return type;
    }
}