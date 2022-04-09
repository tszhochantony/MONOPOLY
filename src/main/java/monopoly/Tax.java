package monopoly;
public class Tax extends Map{
    public Tax(){
        super.location=4;
        super.type = "Tax";
    }
    public int getLocation(){
        return location;
    }
    public String getType(){
        return type;
    }
    public int payTax(int money){
        double tax = money * 0.1;
        return (int)Math.round(tax / 10) * 10;
    }
}