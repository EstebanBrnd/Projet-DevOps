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

    @Override
    public String toString(){
        return "(" + first + ", " + second + ")";
    }

    @Override
    public boolean equals(Object o){
        if (o == this){
            return true;
        }
        if (!(o instanceof Couple)){
            return false;
        }
        Couple c = (Couple) o;
        return first.equals(c.getFirst()) && second.equals(c.getSecond());
    }
}
