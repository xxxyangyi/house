package dao;

import Pojo.QueryVo;
import Pojo.Wrong;

import java.util.List;

public interface WrongMapper {
    List<Wrong> findWrong(QueryVo vo);

    Wrong findById(Integer id);

    void insertWrong(Wrong wrong);

    void deleteWrong(Integer id);
}
