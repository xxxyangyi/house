package dao;

import Pojo.HouseList;
import Pojo.QueryVo;

import java.util.List;

public interface HouseListMapper {
    List<HouseList> selectAll();

    public Integer findhouselistByVoCount(QueryVo vo);

    HouseList findhouseid(String houseid);

    void inserthouse(HouseList houselist);

    void deletehouse(int id);

    HouseList findid(int id);

    HouseList findhouseidupdate(HouseList houselist);

    void updatehouse(HouseList houselist);

    void updatehousestatus(HouseList houselist);

    public void deletehousebyhouseid(String house_id);

    public void updatestatus(HouseList houselist);
}
