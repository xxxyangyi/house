package service;

import Pojo.Paid;
import Pojo.QueryVo;
import Pojo.ZuList;

import java.util.List;

public interface PaidService {
    List<Paid> selectAll(QueryVo vo);

    Double selectSum(QueryVo vo);

    void deletePaid(Integer id);

    List<ZuList> findZuUserList() throws Exception;

    ZuList findzukeZuList(Integer id);

}
