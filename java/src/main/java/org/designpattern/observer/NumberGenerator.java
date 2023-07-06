package org.designpattern.observer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class NumberGenerator {
    private List<Observer> observers = new ArrayList<>();
    public void addObserver(Observer observer) {    // Observer 추가
        observers.add(observer);
    }
    public void deleteObserver(Observer observer) { // Observer 삭제
        observers.remove(observer);
    }
    public void notifyObservers() {               // Observer 통지
        Iterator<Observer> observers = this.observers.iterator();
        while (observers.hasNext()) {
            Observer o = observers.next();
            o.update(this);
        }
    }
    public abstract int getNumber();                // 수를 취득한다.
    public abstract void execute();                 // 수를 생성한다.
}
