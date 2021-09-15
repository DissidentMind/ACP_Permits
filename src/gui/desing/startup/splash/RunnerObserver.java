package gui.desing.startup.splash;

public class RunnerObserver {
    public static void main(String[] args) {
        NewsAgency observable = new NewsAgency();
        NewsChannel observer = new NewsChannel();

        observable.addObserver(observer);
        observable.setNews("news");
        /*
        ObserverManager subject = new ObserverManager();
        new Suscriptor(subject);
        new SuscriberSplashNotify(subject);
        */
        NewsChannel nC = new NewsChannel();
        nC.setNews("Test!");
        // assertEquals(observer.getNews(), "news");
        // observer.getNews();
    }
}
