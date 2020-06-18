package service.response;

public class Status implements GetStatusable{
    private final String status;

    public Status(String status) {
        this.status = status;
    }

    @Override
    public String getStatus() {
        return status;
    }
}
