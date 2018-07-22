package sge.driver;

import java.util.Observable;


@SuppressWarnings("deprecation")
public abstract class Sensor<T> extends Observable {

    private T ultimaMedicion;


    public T medir() {
        this.ultimaMedicion = this.doMedir();

        // notificar al dispositivo que hubo medicion
        this.setChanged();
        this.notifyObservers();

        return this.ultimaMedicion;
    }

    public T getValue() {
        return ultimaMedicion;
    }

    // Template Method (deberia ser private, pero lo dejo public por ahora para usar con Mockito)
    public abstract T doMedir();


}
