package service.response;

import java.util.ArrayList;

public class StatusedArrayList<E> implements GetStatusable{

    private final String status;
    private final ArrayList<E> data;

    public StatusedArrayList(ArrayList<E> data, String status) {
        this.status = status;
        this.data = data;
    }

    public ArrayList<E> getData() {
        return data;
    }

    @Override
    public String getStatus() {
        return status;
    }
}
