package service;

import Pojo.Paid;
import Pojo.QueryVo;
import Pojo.ZuList;
import dao.PaidMapper;
import dao.ZulistMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PaidServiceImpl implements PaidService {
    @Autowired
    private PaidMapper paidMapper;
    @Autowired
    private ZulistMapper zulistMapper;

    @Override
    public List<Paid> selectAll(QueryVo vo) {
        List<Paid> list = paidMapper.selectall(vo);
        return list;
    }

    @Override
    public Double selectSum(QueryVo vo) {
        Double sum = paidMapper.selectsum(vo);
        return sum;
    }

    @Override
    public void deletePaid(Integer id) {
        paidMapper.deletepaid(id);

    }

    @Override
    public List<ZuList> findZuUserList() throws Exception {
        List<ZuList> list = zulistMapper.findzuuserlist();
        return list;
    }

    @Override
    public ZuList findzukeZuList(Integer id) {
        ZuList zulist = zulistMapper.findzukezulist(id);
        return zulist;
    }


}
