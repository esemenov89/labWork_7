package code.model.hibernate;

import javax.persistence.*;

/**
 * Created by admin on 12.05.2017.
 */
@Entity
@Table(name = "USERS", schema = "ADMIN")
public class UsersEntity {
    private String nick;
    private String mail;
    private String password;
    private String role;
    private Long enabled;

    public UsersEntity(){

    }

    public UsersEntity(String nick, String mail, String password, String role, Long enabled) {
        this.nick = nick;
        this.mail = mail;
        this.password = password;
        this.role = role;
        this.enabled = enabled;
    }

    @Id
    @Column(name = "NICK", nullable = false, length = 255)
    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    @Basic
    @Column(name = "MAIL", nullable = true, length = 255)
    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    @Basic
    @Column(name = "PASSWORD", nullable = true, length = 255)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "ROLE", nullable = true, length = 255)
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Basic
    @Column(name = "ENABLED", nullable = true, precision = 0)
    public Long getEnabled() {
        return enabled;
    }

    public void setEnabled(Long enabled) {
        this.enabled = enabled;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UsersEntity that = (UsersEntity) o;

        if (nick != null ? !nick.equals(that.nick) : that.nick != null) return false;
        if (mail != null ? !mail.equals(that.mail) : that.mail != null) return false;
        if (password != null ? !password.equals(that.password) : that.password != null) return false;
        if (role != null ? !role.equals(that.role) : that.role != null) return false;
        if (enabled != null ? !enabled.equals(that.enabled) : that.enabled != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = nick != null ? nick.hashCode() : 0;
        result = 31 * result + (mail != null ? mail.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (role != null ? role.hashCode() : 0);
        result = 31 * result + (enabled != null ? enabled.hashCode() : 0);
        return result;
    }
}
