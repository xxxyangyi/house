package dao;

import Pojo.Apply;
import Pojo.ApplyOut;

import java.util.List;

public interface ApplyMapper {
    void insertApply(Apply apply);

    List<Apply> findApplyList() throws Exception;

    Apply findByHouseId(String house_id);

    void deleteByHouseId(String house_id);

    void updateApplyOut(ApplyOut applyout);
}
