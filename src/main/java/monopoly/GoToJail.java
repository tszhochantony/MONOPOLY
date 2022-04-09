package monopoly;
import java.util.*;
public class GoToJail extends Map{
    private HashMap<String,Integer> prisoner = new HashMap<String,Integer>();
    public GoToJail (){
        super.location = 16;
        super.type = "Go to Jail";
    }

    public int getLocation(){
        return location;
    }

    public String getType(){
        return type;
    }

    public HashMap<String,Integer> getPrisoner(){
        return prisoner;
    }

    public void setPrisoner(String name){
        prisoner.put(name,1);
    }

    public void removePrisoner(String name){
        prisoner.remove(name);
    }

    public void addTime(String name,int time)   {
        prisoner.put(name,time);
    }

    public int getTime(String name){
        return prisoner.get(name);
    }

    public boolean checkPrisoner(String name){
        boolean isPrisoner = false;
        for ( String key : prisoner.keySet() ) {
            if(key.equals(name)){
                isPrisoner = true;
            }
        }
        return isPrisoner;
    }
}