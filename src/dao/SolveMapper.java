package dao;

import Pojo.QueryVo;
import Pojo.Solve;

import java.util.List;

public interface SolveMapper {
    public List<Solve> selectall(QueryVo vo);

    public Integer selectcount(QueryVo vo);

    public void deletesolve(Integer id);

    public void insertsolve(Solve solve);
}
