package gui.controller.pattern.observer;
import java.util.ArrayList;
import java.util.List;

public class ObserverManager {

    private List<EventListener> observers = new ArrayList<EventListener>();
    private int state;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
        notifyAllObservers();
    }

    public void attach(EventListener observer){
        observers.add(observer);
    }

    public void notifyAllObservers(){
        for (EventListener observer : observers) {
            observer.update();
        }
    }
}