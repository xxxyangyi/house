package dao;

import Pojo.QueryVo;
import Pojo.ToPaid;

import java.util.List;

public interface TopaidMapper {
    public void inserttopaid(ToPaid topaid);

    public List<ToPaid> findtopaid(QueryVo vo);

    public ToPaid findbyid(Integer id);

    public void deletetopaid(Integer id);
}
