package monopoly;
    public class SaiKung extends Property{
    public SaiKung(){
        super.location = 17;
        super.price    = 400;
        super.rent     = 10;
        super.name     = "Sai Kung";
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