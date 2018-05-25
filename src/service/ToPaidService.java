package service;

import Pojo.Paid;
import Pojo.QueryVo;
import Pojo.ToPaid;

import java.util.List;

public interface ToPaidService {
    void insertToPaid(ToPaid topaid);

    List<ToPaid> findToPaid(QueryVo vo);

    ToPaid findById(Integer id);

    void gotoPay(Integer id, Paid paid);
}
