package gui.controller.pattern.observer;

import gui.desing.test.MainAppLauncher;

public class SuscriberSplashNotify extends EventListener {
    SuscriberSplashNotify(ObserverManager admonMensaje){
        this.admonMensajes = admonMensaje;
        this.admonMensajes.attach(this);
    }

    @Override
    public void update(){
        System.out.println( "GUI Started..." + admonMensajes.getState());
    }
}

