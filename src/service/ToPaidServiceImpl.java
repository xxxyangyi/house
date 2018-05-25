package service;

import Pojo.Paid;
import Pojo.QueryVo;
import Pojo.ToPaid;
import dao.PaidMapper;
import dao.TopaidMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ToPaidServiceImpl implements ToPaidService {
    @Autowired
    private TopaidMapper topaidMapper;
    @Autowired
    private PaidMapper paidMapper;

    @Override
    public void insertToPaid(ToPaid topaid) {
        topaid.setStatus("租金未缴");
        topaidMapper.insertToPaid(topaid);
    }

    @Override
    public List<ToPaid> findToPaid(QueryVo vo) {
        List<ToPaid> list = topaidMapper.findToPaid(vo);
        return list;
    }

    @Override
    public ToPaid findById(Integer id) {
        ToPaid topaid = topaidMapper.findById(id);
        return topaid;
    }

    @Override
    public void gotoPay(Integer id, Paid paid) {
        paidMapper.insertPaid(paid);
        topaidMapper.deleteToPaid(id);

    }

}
