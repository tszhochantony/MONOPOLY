package monopoly;
public class TuenMun extends Property{
    public TuenMun(){
        super.location=14;
        super.price=400;
        super.rent=20;
        super.name= "Tuen Mun";
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
    public String getOwner(){
        return owner;
    }
    public String getName(){
        return name;
    }
    public String getType(){
        return type;
    }
        public void setOwner(String name){
        super.owner = name;
    }
}