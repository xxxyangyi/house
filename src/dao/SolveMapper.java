package dao;

import Pojo.QueryVo;
import Pojo.Solve;

import java.util.List;

public interface SolveMapper {
    List<Solve> selectAll(QueryVo vo);

    Integer selectCount(QueryVo vo);

    void deleteSolve(Integer id);

    void insertSolve(Solve solve);
}
