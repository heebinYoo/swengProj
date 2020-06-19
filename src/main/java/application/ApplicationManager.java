package application;

import presenter.App;

public class ApplicationManager {

    private static class LazyHolder {
        public static final App app = new AppImpl();
    }

    public static App getInstance(){
        return LazyHolder.app;
    }
}
