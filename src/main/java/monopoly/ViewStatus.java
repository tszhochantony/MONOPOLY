package monopoly;
public class ViewStatus implements Command {
    public void excute(){
        g.viewStatus(false,false);
    }
}
