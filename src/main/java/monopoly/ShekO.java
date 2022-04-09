package monopoly;
public class ShekO extends Property{
    public ShekO(){
        super.location=7;
        super.price=400;
        super.rent=10;
        super.name= "Shek O";
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