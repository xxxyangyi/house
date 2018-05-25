package dao;

import Pojo.ZuList;

import java.util.List;

public interface ZuListMapper {
    void insertZuList(ZuList zulist);

    List<ZuList> findZuUserList() throws Exception;

    ZuList findZuList(String houseId);

    void deleteZuList(String houseId);

    List<ZuList> findZuListByUid(Integer userListId);

    ZuList findZukeZuList(Integer id);
}
