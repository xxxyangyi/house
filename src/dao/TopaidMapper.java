package dao;

import Pojo.QueryVo;
import Pojo.ToPaid;

import java.util.List;

public interface TopaidMapper {
    void insertToPaid(ToPaid topaid);

    List<ToPaid> findToPaid(QueryVo vo);

    ToPaid findById(Integer id);

    void deleteToPaid(Integer id);
}
