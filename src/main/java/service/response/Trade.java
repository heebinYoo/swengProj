package service.response;

public class Trade implements GetStatusable{
    private final String status;
    private final String sellerEmail;
    private final String buyerEmail;
    private final int bookIdx;

    public Trade(String status, String sellerEmail, String buyerEmail, int bookIdx) {
        this.status = status;
        this.sellerEmail = sellerEmail;
        this.buyerEmail = buyerEmail;
        this.bookIdx = bookIdx;
    }

    public String getSellerEmail() {
        return sellerEmail;
    }

    public String getBuyerEmail() {
        return buyerEmail;
    }

    public int getBookIdx() {
        return bookIdx;
    }

    @Override
    public String getStatus() {
        return status;
    }
}
