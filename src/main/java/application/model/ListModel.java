package application.model;


import presenter.Observer;

import java.util.ArrayList;
import java.util.Iterator;

public class ListModel<T> implements Observable {


    private ArrayList<T> data;

    public Iterator<T> getIterator(){
        return data.iterator();
    }

    /**
     * you should apply database first
     * @param newData : new arraylist get from server
     */
    public void replaceData(ArrayList<T> newData){
        data = newData;
        notifyChange();
    }

    /**
     * you should apply database first
     * @param t : the element that deleted in server
     */
    public void deleteElement(T t){
        data.remove(t);
        notifyChange();
    }

    /**
     * you should apply database first
     * @param t : the element that added in server
     */
    public void addElement(T t){
        data.add(t);
        notifyChange();
    }

    private ArrayList<Observer> observerArrayList = new ArrayList<>();

    @Override
    public void notifyChange() {
        for(Observer x : observerArrayList)
            x.update(this);
    }

    @Override
    public void addObserver(Observer observer) {
        observerArrayList.add(observer);
    }

    @Override
    public void deleteObserver(Observer observer) {
        observerArrayList.remove(observer);
    }
}
