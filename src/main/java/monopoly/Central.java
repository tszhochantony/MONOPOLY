package monopoly;
public class Central extends Property{
    public Central(){
        super.location = 2;
        super.price    = 800;
        super.rent     = 90;
        super.name     = "Central";
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