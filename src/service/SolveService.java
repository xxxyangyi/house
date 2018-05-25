package service;

import Pojo.QueryVo;
import Pojo.Solve;
import Pojo.Wrong;

import java.util.List;

public interface SolveService {
    List<Solve> selectAll(QueryVo vo);

    Integer selectCount(QueryVo vo);

    void deleteSolve(Integer id);

    List<Wrong> findWrong(QueryVo vo);

    Wrong findById(Integer id);

    void insertWrong(Wrong wrong);

    void gotoSolve(Integer id, Solve solve);
}
