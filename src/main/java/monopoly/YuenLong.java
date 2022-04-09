package monopoly;
public class YuenLong extends Property{
    public YuenLong(){
        super.location = 18;
        super.price    = 400;
        super.rent     = 25;
        super.name     = "Yuen Long";
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