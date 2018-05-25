package dao;

import Pojo.HouseList;
import Pojo.QueryVo;

import java.util.List;

public interface HouseListMapper {
    List<HouseList> selectAll();

    Integer findHouseListByVoCount(QueryVo vo);

    HouseList findHouseId(String houseId);

    void insertHouse(HouseList houseList);

    void deleteHouse(int id);

    HouseList findId(int id);

    HouseList findHouseIdUpdate(HouseList houseList);

    void updateHouse(HouseList houseList);

    void updateHouseStatus(HouseList houseList);

    void deleteHouseByHouseId(String houseId);

    void updateStatus(HouseList houseList);
}
