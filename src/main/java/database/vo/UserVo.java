package database.vo;

import java.util.Objects;

public class UserVo {
    final private String id;
    final private String pw;
    final private String name;
    final private String phone;
    final private String email;
    final private boolean active;
    final private boolean manage;

    public UserVo(String id, String pw, String name, String phone, String email, boolean active, boolean manage){
        this.id = id;
        this.pw = pw;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.active = active;
        this.manage = manage;
    }

    @Override
    public String toString() {
        return

                id +
                        "," +

                        pw +
                        "," +

                        name +
                        "," +

                        phone +
                        "," +

                        email +
                        "," +

                        active +
                        "," +

                        manage +
                        "";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserVo userVo = (UserVo) o;
        return getId().equals(userVo.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    public String getId() {
        return id;
    }

    public String getPw() {
        return pw;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public boolean isActive() {
        return active;
    }

    public boolean isManage() {
        return manage;
    }
}
