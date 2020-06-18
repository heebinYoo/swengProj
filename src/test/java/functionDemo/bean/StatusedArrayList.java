package functionDemo.bean;

import java.util.ArrayList;

public class StatusedArrayList<E>{

    public String status;
    public ArrayList<E> data;

    public StatusedArrayList(ArrayList<E> data, String status) {
        this.status = status;
        this.data = data;
    }
}
