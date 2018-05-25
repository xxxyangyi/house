package service;

import Pojo.ZuList;

import java.util.List;

public interface ZuListService {
    void insertZuList(ZuList zulist);

    List<ZuList> findZuUserList() throws Exception;

    ZuList findZuList(String house_id);

    void deleteZuList(String house_id);

    List<ZuList> findZuListByUid(Integer userListId);
}
