package monopoly;
public abstract class Property extends Map{
    int rent;
    int price; 
    String name;
    String owner;
    String type = "property";
    public abstract int getPrice();
    public abstract int getRent();
    public abstract String getOwner();
    public abstract String getName();
    public abstract void setOwner(String name);
}