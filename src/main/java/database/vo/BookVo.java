package database.vo;

import java.util.Objects;

public class BookVo {
    final private int idx;
    final private String uid;
    final private String title;
    final private String publisher;
    final private String writer;
    final private int pyear;
    final private int price;
    final private int condition;
    final private String ISBN;


    public BookVo(int idx, String uid, String title, String publisher, String writer, int pyear, int price, int condition, String ISBN) {
        this.idx = idx;
        this.uid = uid;
        this.title = title;
        this.publisher = publisher;
        this.writer = writer;
        this.pyear = pyear;
        this.price = price;
        this.condition = condition;
        this.ISBN = ISBN;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookVo bookVo = (BookVo) o;
        return getIdx() == bookVo.getIdx();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIdx());
    }


    @Override
    public String toString() {
        return

                idx +
                        "," +

                        uid +
                        "," +

                        title +
                        "," +

                        publisher +
                        "," +

                        writer +
                        "," +

                        pyear +
                        "," +

                        price +
                        "," +

                        condition +
                        "," +

                        ISBN +
                        "";
    }

    public int getIdx() {
        return idx;
    }

    public String getUid() {
        return uid;
    }

    public String getTitle() {
        return title;
    }

    public String getPublisher() {
        return publisher;
    }

    public String getWriter() {
        return writer;
    }

    public int getPyear() {
        return pyear;
    }

    public int getPrice() {
        return price;
    }

    public int getCondition() {
        return condition;
    }

    public String getISBN() {
        return ISBN;
    }
}
