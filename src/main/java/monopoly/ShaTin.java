package monopoly;
public class ShaTin extends Property{
    public ShaTin(){
        super.location = 12;
        super.price    = 700;
        super.rent     = 75;
        super.name     = "Sha Tin";
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