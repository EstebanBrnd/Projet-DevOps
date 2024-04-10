package projetdevops;

public class Couple<E,V> {

    private E first;
    private V second;

    Couple(E first, V second) {
        this.first = first;
        this.second = second;
    }

    public E getFirst() {
        return first;
    }

    public V getSecond() {
        return second;
    }
}
