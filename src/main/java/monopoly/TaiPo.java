package monopoly;
public class TaiPo extends Property{
    public TaiPo(){
        super.location=15;
        super.price=500;
        super.rent=25;
        super.name="Tai Po";
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