package monopoly;
import java.util.concurrent.ThreadLocalRandom;
public class Chance extends Map{
    public Chance(int location){
       super.location = location;
       super.type = "Chance";
    }
    public int getLocation(){
        return super.location;
    }
    public String getType(){
        return type;
    }
    public int getChance(){
        return ThreadLocalRandom.current().nextInt(-200, 200 + 1);
    }
}