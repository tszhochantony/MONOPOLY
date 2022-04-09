package monopoly;
public class FreePark extends Map{
    public FreePark(){
        super.location = 11;
        super.type = "Free Park";
    }
    public int getLocation(){
        return location;
    }
    public String getType(){
        return type;
    }
}