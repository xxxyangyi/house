package service;

import Pojo.ApplyOut;
import Pojo.Checkout;
import Pojo.ZuList;
import dao.ApplyoutMapper;
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
    private ApplyoutMapper applyoutMapper;
    @Autowired
    private HouseListMapper houselistMapper;
    @Autowired
    private ContractMapper hetongMapper;
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
        applyoutMapper.insertapplyout(applyout);

    }

    @Override
    public List<ApplyOut> findAllApplyOut() {
        List<ApplyOut> list = applyoutMapper.findallapplyout();
        return list;
    }

    @Override
    public void updateApplyOut(ApplyOut applyout) {

        applyoutMapper.updateapplyout(applyout);
    }

    @Override
    public void agreeApplyOut(Integer id) {
        ApplyOut applyout = applyoutMapper.findbyid(id);
        houselistMapper.deleteHouseByHouseId(applyout.getHouseId());
        hetongMapper.deleteContract(applyout.getHouseId());
        Checkout checkout = new Checkout();
        checkout.setHouse_id(applyout.getHouseId());
        checkout.setAddress(applyout.getAddress());
        checkout.setStatus("已退租");
        checkout.setUserlist_id(applyout.getUserListId());
        checkoutMapper.insertcheckout(checkout);
        applyout.setStatus("已同意");
        applyoutMapper.updateapplyoutbyhouse(applyout);
        zulistMapper.deleteZuList(applyout.getHouseId());
    }

    @Override
    public void deleteApplyOut(Integer id) {

        applyoutMapper.deleteapplyout(id);
    }


}
