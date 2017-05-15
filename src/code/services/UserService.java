package code.services;

import code.model.pojo.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;

/**
 *
 */
public interface UserService {

    User auth(String login, String password);
    User findUserByLogin(String login);
    ArrayList<User> getAllUsers();
    User validateUser(String login, String password, String mail);
    void addUser(User user);
    void lockOrUnlockUser(String nick,int lock);
}
