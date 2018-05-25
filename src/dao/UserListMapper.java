package dao;

import Pojo.UserList;

import java.util.List;

public interface UserListMapper {
    UserList findhasuserlist(Integer user_id);

    UserList checkuserlist(String idcard);

    void insertuserlist(UserList userList);

    void updateuserlist(UserList userList);

    UserList finduserlistupdate(UserList userList);

    public List<UserList> getUserzuList(Integer id);

    public List<UserList> getmycheckout(Integer id);

    public List<UserList> getmyapply(Integer id);

    public List<UserList> getmyapplyout(Integer id);

    public List<UserList> findalluserlist();

    public void deleteuser(Integer id);

    public void deleteuserlist(Integer id);
}
