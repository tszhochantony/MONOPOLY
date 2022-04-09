package monopoly;
public class Stanley extends Property{
    public Stanley(){
        super.location = 5;
        super.price    = 600;
        super.rent     = 60;
        super.name     = "Stanley";
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