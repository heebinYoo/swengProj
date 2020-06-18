package service.response;

import java.util.ArrayList;

public class StatusedString {
    private final String status;
    private final String data;

    public StatusedString(String status, String data) {
        this.status = status;
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public String getData() {
        return data;
    }
}
