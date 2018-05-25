package service;

import Pojo.ZuList;
import dao.ZulistMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ZuListServiceImpl implements ZuListService {

    @Autowired
    private ZulistMapper zulistMapper;

    @Override
    public void insertZuList(ZuList zulist) {
        zulistMapper.insertzulist(zulist);

    }

    @Override
    public List<ZuList> findZuUserList() throws Exception {
        List<ZuList> zuList = zulistMapper.findzuuserlist();
        return zuList;
    }

    @Override
    public ZuList findZuList(String house_id) {
        ZuList zulist = zulistMapper.findzulist(house_id);
        return zulist;
    }

    @Override
    public void deleteZuList(String house_id) {
        zulistMapper.deletezulist(house_id);

    }

    @Override
    public List<ZuList> findZuListByUid(Integer userListId) {
        List<ZuList> zuList = zulistMapper.findzulistbyuid(userListId);
        return zuList;
    }

}
