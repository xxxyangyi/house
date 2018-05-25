package service;

import Pojo.Apply;
import Pojo.HouseList;
import dao.ApplyMapper;
import dao.HouseListMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ApplyServiceImpl implements ApplyService {

    @Autowired
    private ApplyMapper applyMapper;
    @Autowired
    private HouseListMapper houselistMapper;

    @Override
    public void insertApply(Apply apply) {
        applyMapper.insertApply(apply);

    }

    @Override
    public List<Apply> findApplyList() throws Exception {
        List<Apply> apply = applyMapper.findApplyList();
        return apply;
    }

    @Override
    public Apply findByHouseId(String house_id) {
        Apply apply = applyMapper.findByHouseId(house_id);
        return apply;
    }

    @Override
    public void deleteByHouseId(String house_id) {
        applyMapper.deleteByHouseId(house_id);

    }

    @Override
    public void refuseApply(HouseList houselist) {
        houselistMapper.updateStatus(houselist);
        applyMapper.deleteByHouseId(houselist.getHouseId());
    }


}
