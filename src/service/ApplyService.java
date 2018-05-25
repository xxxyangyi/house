package service;

import Pojo.Apply;
import Pojo.HouseList;

import java.util.List;

public interface ApplyService {

    void insertApply(Apply apply);

    List<Apply> findApplyList() throws Exception;

    Apply findByHouseId(String house_id);

    void deleteByHouseId(String house_id);

    void refuseApply(HouseList houselist);
}
