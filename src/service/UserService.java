package service;

import Pojo.User;

import java.util.List;

public interface UserService {

    List<User> userList() throws Exception;

    User login(User user) throws Exception;

}
