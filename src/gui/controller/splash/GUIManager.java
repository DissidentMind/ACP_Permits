package gui.controller.splash;
import java.util.ArrayList;
import java.util.List;

public class GUIManager {
    private final List<GUIListen> observers = new ArrayList<GUIListen>();
    private int state;

    public int getState() {
        return state;
    }
    public void setState(int state) {
        this.state = state;
        notifyAllObservers();
    }
    public void attach(GUIListen observer){
        observers.add(observer);
    }
    public void notifyAllObservers(){
        for (GUIListen listGui : observers) {
            listGui.update();
        }
    }

}
