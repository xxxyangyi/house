package dao;

import Pojo.Paid;
import Pojo.QueryVo;

import java.util.List;

public interface PaidMapper {
    public List<Paid> selectall(QueryVo vo);

    public Double selectsum(QueryVo vo);

    public void deletepaid(Integer id);

    public void insertpaid(Paid paid);
}
