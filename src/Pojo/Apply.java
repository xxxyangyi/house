package Pojo;

public class Apply {
    private Integer id;
    private String houseId;
    private String address;
    private double area;
    private double price;
    private Integer userListId;
    private String status;
    private UserList userList;

    public UserList getUserList() {
        return userList;
    }

    public void setUserList(UserList userList) {
        this.userList = userList;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHouseId() {
        return houseId;
    }

    public void setHouseId(String houseId) {
        this.houseId = houseId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Integer getUserListId() {
        return userListId;
    }

    public void setUserListId(Integer userListId) {
        this.userListId = userListId;
    }

    @Override
    public String toString() {
        return "Apply [id=" + id + ", houseId=" + houseId + ", address=" + address + ", area=" + area + ", price=" + price
                + ", userListId=" + userListId + ", status=" + status + ", userList=" + userList + "]";
    }


}
