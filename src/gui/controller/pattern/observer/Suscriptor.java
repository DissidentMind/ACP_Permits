package gui.controller.pattern.observer;

public class Suscriptor extends EventListener{

    public Suscriptor(ObserverManager admonMensajes){
        this.admonMensajes = admonMensajes;
        this.admonMensajes.attach(this);
    }

    @Override
    public void update() {
        System.out.println( "Existio un Cambio de Valor: " + Integer.toBinaryString( admonMensajes.getState() ) );
    }
}