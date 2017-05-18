package code.services;

import code.model.dto.UserDTO;
import code.model.hibernate.UsersEntity;
import java.util.ArrayList;

/**
 *
 */
public interface UserService {

    UsersEntity findUserByLogin(String login);
    ArrayList<UserDTO> getAllUsers();
    UsersEntity validateUser(String login, String password, String mail);
    void addUser(UsersEntity user);
    void lockOrUnlockUser(String nick,Long lock);
}
