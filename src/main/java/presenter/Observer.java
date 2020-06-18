package presenter;

import application.model.Observable;

public interface Observer {
    void update(Observable observable);
}
