package gui.controller.pattern.observer;

public class RunObserver {

    public static void main(String[] args) {
        ObserverManager subject = new ObserverManager();
        new Suscriptor(subject);
        new SuscriberSplashNotify(subject);

        int max = 434;
        int min = 109;
        int range = max - min + 1;

        for (int i = 0; i < 5 ; i++) {
            int rand = (int)(Math.random() * range) + min;
            System.out.println("Ejecuando Emisor: "+rand);
            subject.setState(rand);
        }

    }
}
