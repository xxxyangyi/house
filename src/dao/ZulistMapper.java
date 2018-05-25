package dao;

import Pojo.ZuList;

import java.util.List;

public interface ZulistMapper {
    public void insertzulist(ZuList zulist);

    public List<ZuList> findzuuserlist() throws Exception;

    ZuList findzulist(String house_id);

    public void deletezulist(String house_id);

    public List<ZuList> findzulistbyuid(Integer userlist_id);

    public ZuList findzukezulist(Integer id);
}
