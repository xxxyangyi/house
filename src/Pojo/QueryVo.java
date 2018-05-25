package Pojo;

public class QueryVo {
    private String zuName;
    private String fromDate;
    private String toDate;
    private Integer userListId;

    public Integer getUserListId() {
        return userListId;
    }

    public void setUserListId(Integer userListId) {
        this.userListId = userListId;
    }

    public String getZuName() {
        return zuName;
    }

    public void setZuName(String zuName) {
        this.zuName = zuName;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }


}
