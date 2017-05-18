package code.model.dto;

/**
 * Created by admin on 18.05.2017.
 */
public class UserDTO {
    private String nick;
    private String role;
    private Long enabled;


    public UserDTO(String nick, String role, Long enabled) {
        this.nick = nick;
        this.role = role;
        this.enabled = enabled;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Long getEnabled() {
        return enabled;
    }

    public void setEnabled(Long enabled) {
        this.enabled = enabled;
    }
}
