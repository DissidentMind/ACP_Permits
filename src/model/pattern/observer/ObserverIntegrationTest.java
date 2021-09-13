package model.pattern.observer;

public class ObserverIntegrationTest {
    public static void main(String[] args) {

        int max = 434;
        int min = 109;
        int range = max - min + 1;

        System.out.printf(" Running Test... ");

        NewsAgency observable = new NewsAgency();
        NewsChannel observer = new NewsChannel();

        NewsChannel observer2 = new NewsChannel();

        observable.addObserver(observer);
        observable.addObserver(observer2);

        //new Suscriptor(observer2);
        //observable.setNews("news");

        for (int i = 0; i < 5 ; i++) {
            int rand = (int)(Math.random() * range) + min;
            System.out.println("Ejecuando Emisor: "+rand);
            //System.out.println("Value: "+observer.getNews().concat(" - "+i));
            observable.setNews("news"+i);
        }

        /*
        ObserverManager subject = new ObserverManager();
        new Suscriptor(subject);
        new SuscriberSplashNotify(subject);

         */
    }
}