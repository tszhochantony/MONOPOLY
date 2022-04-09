package monopoly;
public class MongKok extends Property{
    public MongKok(){
        super.location = 8;
        super.price    = 500;
        super.rent     = 40;
        super.name     = "Mong Kok";
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