package dao;

import Pojo.Checkout;

import java.util.List;

public interface CheckoutMapper {
    void insertCheckout(Checkout checkout);

    List<Checkout> getAllCheckout();

    void deleteCheckout(Integer id);
}
