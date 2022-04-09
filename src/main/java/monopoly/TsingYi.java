package monopoly;
public class TsingYi extends Property{
    public TsingYi(){
        super.location = 10;
        super.price    = 400;
        super.rent     = 15;
        super.name     = "Tsing Yi";
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