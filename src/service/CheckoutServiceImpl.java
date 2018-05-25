package service;

import Pojo.Checkout;
import dao.CheckoutMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CheckoutServiceImpl implements CheckoutService {

    @Autowired
    private CheckoutMapper checkoutMapper;

    @Override
    public void insertCheckOut(Checkout checkout) {
        checkoutMapper.insertcheckout(checkout);

    }

    @Override
    public List<Checkout> getAllCheckOut() {
        List<Checkout> checkout = checkoutMapper.getallcheckout();
        return checkout;
    }

    @Override
    public void deleteCheckOut(Integer id) {

        checkoutMapper.deletecheckout(id);
    }


}
