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
        UserList userList = userListMapper.findhasuserlist(userId);
        return userList;
    }

    @Override
    public UserList checkUserList(String idCard) {
        UserList userList = userListMapper.checkuserlist(idCard);
        return userList;
    }

    @Override
    public void insertUserList(UserList userList) {
        userListMapper.insertuserlist(userList);

    }

    @Override
    public void updateUserList(UserList userList) {
        userListMapper.updateuserlist(userList);

    }

    @Override
    public UserList findUserListUpdate(UserList userList) {
        UserList list = userListMapper.finduserlistupdate(userList);
        return list;
    }

    @Override
    public List<UserList> getUserZuList(Integer id) {
        List<UserList> userList = userListMapper.getUserzuList(id);
        for (UserList list : userList) {
            System.out.println(list);
        }
        return userList;
    }

    @Override
    public List<UserList> getMyCheckout(Integer id) {
        List<UserList> list = userListMapper.getmycheckout(id);
        return list;
    }

    @Override
    public List<UserList> getMyApply(Integer id) {
        List<UserList> list = userListMapper.getmyapply(id);
        return list;
    }

    @Override
    public List<UserList> getMyApplyOut(Integer id) {
        List<UserList> list = userListMapper.getmyapplyout(id);
        return list;
    }

    @Override
    public List<UserList> findAllUserList() {
        List<UserList> list = userListMapper.findalluserlist();
        return list;
    }

    @Override
    public void deleteUserList(Integer id) {
        userListMapper.deleteuserlist(id);
        userListMapper.deleteuser(id);
    }

}
