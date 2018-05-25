package Pojo;

import java.util.List;

public class UserList {
    private Integer id;
    private String name;
    private String idCard;
    private String phone;
    private Integer user_id;
    private List<Apply> apply;
    private List<ZuList> zuList;
    private List<Checkout> checkout;
    private List<ApplyOut> applyOut;
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<ApplyOut> getApplyOut() {
        return applyOut;
    }

    public void setApplyOut(List<ApplyOut> applyOut) {
        this.applyOut = applyOut;
    }

    public List<Checkout> getCheckout() {
        return checkout;
    }

    public void setCheckout(List<Checkout> checkout) {
        this.checkout = checkout;
    }

    public List<ZuList> getZuList() {
        return zuList;
    }

    public void setZuList(List<ZuList> zuList) {
        this.zuList = zuList;
    }

    public List<Apply> getApply() {
        return apply;
    }

    public void setApply(List<Apply> apply) {
        this.apply = apply;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

}
