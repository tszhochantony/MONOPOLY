package monopoly;
public class ViewPersonalStatus implements Command {
    public void excute(){
        g.viewStatus(true,false);
    }    
}
