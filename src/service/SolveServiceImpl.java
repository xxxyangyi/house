package service;

import Pojo.QueryVo;
import Pojo.Solve;
import Pojo.Wrong;
import dao.SolveMapper;
import dao.WrongMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SolveServiceImpl implements SolveService {
    @Autowired
    private SolveMapper solveMapper;
    @Autowired
    private WrongMapper wrongMapper;

    @Override
    public List<Solve> selectAll(QueryVo vo) {
        List<Solve> list = solveMapper.selectAll(vo);
        return list;
    }

    @Override
    public Integer selectCount(QueryVo vo) {
        Integer count = solveMapper.selectCount(vo);
        return count;
    }

    @Override
    public void deleteSolve(Integer id) {
        solveMapper.deleteSolve(id);

    }

    @Override
    public List<Wrong> findWrong(QueryVo vo) {
        List<Wrong> list = wrongMapper.findWrong(vo);
        return list;
    }

    @Override
    public Wrong findById(Integer id) {
        Wrong wrong = wrongMapper.findById(id);
        return wrong;
    }

    @Override
    public void insertWrong(Wrong wrong) {
        wrong.setStatus("待处理");
        wrongMapper.insertWrong(wrong);

    }

    @Override
    public void gotoSolve(Integer id, Solve solve) {
        solveMapper.insertSolve(solve);
        wrongMapper.deleteWrong(id);

    }

}
