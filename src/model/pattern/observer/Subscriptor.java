package model.pattern.observer;

import gui.controller.pattern.observer.EventListener;

public class Subscriptor extends EventListener {
    Subscriptor(NewsAgency admonMensaje){
        //this.admonMensajes = admonMensaje;
        this.admonMensajes.attach(this);
    }

    @Override
    public void update(){
        System.out.println( "GUI Started..." + admonMensajes.getState());
    }
}