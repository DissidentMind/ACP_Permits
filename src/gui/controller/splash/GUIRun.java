package gui.controller.splash;

public class GUIRun {

    public static void main(String[] args) throws InterruptedException {
        GUIManager gMan = new GUIManager();
        new GUISplash(gMan);

        int max = 434;
        int min = 109;
        int range = max - min + 1;

        for (int i = 0; i < 5 ; i++) {
            int rand = (int)(Math.random() * range) + min;
            Thread.sleep(1000);
            System.out.println("Ejecuando Emisor: "+rand);
            gMan.setState(rand);
        }

    }

}
