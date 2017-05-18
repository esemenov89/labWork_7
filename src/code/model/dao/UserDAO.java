package code.model.dao;

import code.model.dto.UserDTO;
import code.model.hibernate.UsersEntity;

import java.util.ArrayList;
/**
 *
 */
public interface UserDAO {
    UsersEntity findUserByLogin(String login);
    UserDTO findUserByMail(String mail);
    ArrayList<UserDTO> getAllUsers();
    void addUser(UsersEntity user);
    void lockOrUnlockUser(String nick,Long lock);
}
