package code.services;

import code.model.hibernate.UsersEntity;
import code.model.pojo.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;

/**
 *
 */
public interface UserService {

    UsersEntity findUserByLogin(String login);
    ArrayList<UsersEntity> getAllUsers();
    UsersEntity validateUser(String login, String password, String mail);
    void addUser(UsersEntity user);
    void lockOrUnlockUser(String nick,Long lock);
}
