package application.model;

import presenter.Observer;

public interface Observable {
    void notifyChange();
    void addObserver(Observer observer);
    void deleteObserver(Observer observer);
}
