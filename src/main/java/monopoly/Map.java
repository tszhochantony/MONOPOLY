package monopoly;
public abstract class Map implements java.io.Serializable{
    int location;
    String type;
    public abstract int getLocation();
    public abstract String getType();
}
