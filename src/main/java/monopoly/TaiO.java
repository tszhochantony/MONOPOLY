package monopoly;
public class TaiO extends Property{
    public TaiO(){
        super.location=20;
        super.price=600;
        super.rent=25;
        super.name= "Tai O";
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