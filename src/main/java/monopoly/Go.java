package monopoly;
public class Go extends Map{
    public Go (){
        super.location = 1;
        super.type = "Go";
    }
    public int getLocation(){
        return location;
    }
    public String getType(){
        return type;
    }
}