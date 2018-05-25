package service;

import Pojo.UserList;
import dao.UserListMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserListServiceImpl implements UserListService {

    @Autowired
    private UserListMapper userListMapper;

    @Override
    public UserList findHasUserList(Integer userId) {
        UserList userList = userListMapper.findHasUserList(userId);
        return userList;
    }

    @Override
    public UserList checkUserList(String idCard) {
        UserList userList = userListMapper.checkUserList(idCard);
        return userList;
    }

    @Override
    public void insertUserList(UserList userList) {
        userListMapper.insertUserList(userList);

    }

    @Override
    public void updateUserList(UserList userList) {
        userListMapper.updateUserList(userList);

    }

    @Override
    public UserList findUserListUpdate(UserList userList) {
        UserList list = userListMapper.findUserListUpdate(userList);
        return list;
    }

    @Override
    public List<UserList> getUserZuList(Integer id) {
        List<UserList> userList = userListMapper.getUserZuList(id);
        for (UserList list : userList) {
            System.out.println(list);
        }
        return userList;
    }

    @Override
    public List<UserList> getMyCheckout(Integer id) {
        List<UserList> list = userListMapper.getMyCheckout(id);
        return list;
    }

    @Override
    public List<UserList> getMyApply(Integer id) {
        List<UserList> list = userListMapper.getMyApply(id);
        return list;
    }

    @Override
    public List<UserList> getMyApplyOut(Integer id) {
        List<UserList> list = userListMapper.getMyApplyOut(id);
        return list;
    }

    @Override
    public List<UserList> findAllUserList() {
        List<UserList> list = userListMapper.findAllUserList();
        return list;
    }

    @Override
    public void deleteUserList(Integer id) {
        userListMapper.deleteUserList(id);
        userListMapper.deleteUser(id);
    }

}
