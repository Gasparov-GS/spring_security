package web.dao;

import web.model.User;

import java.util.List;
import java.util.Optional;

public interface UserDao {
    void addUser(User user);
    void removeUser(int id);
    List<User> getAllUsers();
    Optional<User> findUserById(int id);
    Optional<User> findUserByName(String username);
}
