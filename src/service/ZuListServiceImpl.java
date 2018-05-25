package service;

import Pojo.ZuList;
import dao.ZuListMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ZuListServiceImpl implements ZuListService {

    @Autowired
    private ZuListMapper zulistMapper;

    @Override
    public void insertZuList(ZuList zulist) {
        zulistMapper.insertZuList(zulist);

    }

    @Override
    public List<ZuList> findZuUserList() throws Exception {
        List<ZuList> zuList = zulistMapper.findZuUserList();
        return zuList;
    }

    @Override
    public ZuList findZuList(String house_id) {
        ZuList zulist = zulistMapper.findZuList(house_id);
        return zulist;
    }

    @Override
    public void deleteZuList(String house_id) {
        zulistMapper.deleteZuList(house_id);

    }

    @Override
    public List<ZuList> findZuListByUid(Integer userListId) {
        List<ZuList> zuList = zulistMapper.findZuListByUid(userListId);
        return zuList;
    }

}
