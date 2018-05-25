package service;

import Pojo.HouseList;

import java.util.List;

public interface HouseListService {
    List<HouseList> selectAll();

    HouseList findHouseId(String houseId);

    void insertHouse(HouseList houseList);

    void deleteHouse(int id);

    HouseList findId(int id);

    HouseList findHouseIdUpdate(HouseList houseList);

    void updateHouse(HouseList houseList);

    void updateHouseStatus(HouseList houseList);

    void deleteHouseByHouseId(String house_id);
}
