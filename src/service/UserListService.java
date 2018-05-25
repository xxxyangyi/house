package service;

import Pojo.UserList;

import java.util.List;

public interface UserListService {
    UserList findHasUserList(Integer userId);

    UserList checkUserList(String idCard);

    void insertUserList(UserList userList);

    void updateUserList(UserList userList);

    UserList findUserListUpdate(UserList userList);

    List<UserList> getUserZuList(Integer id);

    List<UserList> getMyCheckout(Integer id);

    List<UserList> getMyApply(Integer id);

    List<UserList> getMyApplyOut(Integer id);

    List<UserList> findAllUserList();

    void deleteUserList(Integer id);
}
