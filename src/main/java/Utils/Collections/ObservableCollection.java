package Utils.Collections;

import java.util.ArrayList;

import lombok.Getter;
import lombok.Setter;


public class ObservableCollection<E> {
    @Getter @Setter private ArrayList<E> elements;
    private ArrayList<Observer> observers;

    public ObservableCollection(ArrayList<E> list) {
        elements = list;
        observers = new ArrayList<>();
    }

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void removeObserver(Observer observer) {
        observers.removeIf(o -> o == observer);
    }

    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update();
        }
    }

    public boolean add(E e) {
        boolean result = elements.add(e);
        notifyObservers();
        return result;
    }

    public void add(int index, E element) {
        elements.add(index, element);
        notifyObservers();
    }
    
    public boolean remove(Object o) {
        boolean result = elements.remove(o);
        notifyObservers();
        return result;
    }

    public E remove(int index) {
        E result = elements.remove(index);
        notifyObservers();
        return result;
    }

    public void clear() {
        elements.clear();
        notifyObservers();
    }

    public E get(int index) {
        return elements.get(index);
    }

    public E set(int index, E element) {
        E result = elements.set(index, element);
        notifyObservers();
        return result;
    }

    public int size() {
        return elements.size();
    }

    public boolean isEmpty() {
        return elements.isEmpty();
    }

    public boolean contains(Object o) {
        return elements.contains(o);
    }

    public int indexOf(Object o) {
        return elements.indexOf(o);
    }

    public int lastIndexOf(Object o) {
        return elements.lastIndexOf(o);
    }

    public Object[] toArray() {
        return elements.toArray();
    }

    public <T> T[] toArray(T[] a) {
        return elements.toArray(a);
    }
}
