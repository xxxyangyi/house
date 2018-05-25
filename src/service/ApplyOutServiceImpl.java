package service;

import Pojo.ApplyOut;
import Pojo.Checkout;
import Pojo.ZuList;
import dao.ApplyOutMapper;
import dao.CheckoutMapper;
import dao.ContractMapper;
import dao.HouseListMapper;
import dao.ZuListMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ApplyOutServiceImpl implements ApplyOutService {

    @Autowired
    private ApplyOutMapper applyOutMapper;
    @Autowired
    private HouseListMapper houseListMapper;
    @Autowired
    private ContractMapper contractMapper;
    @Autowired
    private CheckoutMapper checkoutMapper;
    @Autowired
    private ZuListMapper zulistMapper;

    @Override
    public void insertApplyOut(ZuList zulist) {
        ApplyOut applyout = new ApplyOut();
        applyout.setHouseId(zulist.getHouseId());
        applyout.setAddress(zulist.getAddress());
        applyout.setStatus("申请中");
        applyout.setUserListId(zulist.getUserListId());
        applyOutMapper.insertApplyOut(applyout);

    }

    @Override
    public List<ApplyOut> findAllApplyOut() {
        List<ApplyOut> list = applyOutMapper.findAllApplyOut();
        return list;
    }

    @Override
    public void updateApplyOut(ApplyOut applyout) {
        applyOutMapper.updateApplyOut(applyout);
    }

    @Override
    public void agreeApplyOut(Integer id) {
        ApplyOut applyout = applyOutMapper.findById(id);
        houseListMapper.deleteHouseByHouseId(applyout.getHouseId());
        contractMapper.deleteContract(applyout.getHouseId());
        Checkout checkout = new Checkout();
        checkout.setHouseId(applyout.getHouseId());
        checkout.setAddress(applyout.getAddress());
        checkout.setStatus("已退租");
        checkout.setUserListId(applyout.getUserListId());
        checkoutMapper.insertCheckout(checkout);
        applyout.setStatus("已同意");
        applyOutMapper.updateApplyOut(applyout);
        zulistMapper.deleteZuList(applyout.getHouseId());
    }

    @Override
    public void deleteApplyOut(Integer id) {
        applyOutMapper.deleteApplyOut(id);
    }


}
