package database.service;

import database.entity.User;

import java.util.List;

public interface UserService {

    void save(User user);

    List<User> getUsers();

    User findByUserName(String username);
}
