package monopoly;
public class WanChai extends Property{
    public WanChai(){
        super.location = 3;
        super.price    = 700;
        super.rent     = 65;
        super.name     = "Wan Chai";
    }
    public int getLocation(){
        return location;
    }
    public int getPrice(){
        return price;
    }
    public int getRent(){
        return rent;
    }
    public String getType(){
        return type;
    }
    public String getOwner(){
        return owner;
    }
    public String getName(){
        return name;
    }
    public void setOwner(String name){
        super.owner = name;
    }
}