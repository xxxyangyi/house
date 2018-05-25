package service;

import Pojo.Checkout;

import java.util.List;

public interface CheckoutService {
    void insertCheckOut(Checkout checkout);

    List<Checkout> getAllCheckOut();

    void deleteCheckOut(Integer id);
}
