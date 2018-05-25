package service;

import Pojo.HouseList;
import dao.HouseListMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HouseListServiceImpl implements HouseListService {

    @Autowired
    private HouseListMapper houseListMapper;

    @Override
    public List<HouseList> selectAll() {
        List<HouseList> houseList = houseListMapper.selectAll();
        return houseList;
    }

    @Override
    public HouseList findHouseId(String houseId) {
        HouseList houseList = houseListMapper.findHouseId(houseId);
        return houseList;
    }

    @Override
    public void insertHouse(HouseList houseList) {
        // TODO Auto-generated method stub
        houseListMapper.insertHouse(houseList);
    }

    @Override
    public void deleteHouse(int id) {
        // TODO Auto-generated method stub
        houseListMapper.deleteHouse(id);
    }

    @Override
    public HouseList findHouseIdUpdate(HouseList houselist) {
        HouseList list = houseListMapper.findHouseIdUpdate(houselist);
        return list;
    }

    @Override
    public void updateHouse(HouseList houseList) {
        houseListMapper.updateHouse(houseList);

    }

    @Override
    public HouseList findId(int id) {
        HouseList list = houseListMapper.findId(id);
        return list;
    }

    @Override
    public void updateHouseStatus(HouseList houseList) {
        // TODO Auto-generated method stub
        houseListMapper.updateHouseStatus(houseList);
    }

    @Override
    public void deleteHouseByHouseId(String houseId) {
        houseListMapper.deleteHouseByHouseId(houseId);

    }


}
