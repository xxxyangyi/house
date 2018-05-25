package dao;

import Pojo.Paid;
import Pojo.QueryVo;

import java.util.List;

public interface PaidMapper {
    List<Paid> selectAll(QueryVo vo);

    Double selectSum(QueryVo vo);

    void deletePaid(Integer id);

    void insertPaid(Paid paid);
}
