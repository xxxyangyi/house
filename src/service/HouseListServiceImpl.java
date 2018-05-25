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
        HouseList houseList = houseListMapper.findhouseid(houseId);
        return houseList;
    }

    @Override
    public void insertHouse(HouseList houseList) {
        // TODO Auto-generated method stub
        houseListMapper.inserthouse(houseList);
    }

    @Override
    public void deleteHouse(int id) {
        // TODO Auto-generated method stub
        houseListMapper.deletehouse(id);
    }

    @Override
    public HouseList findHouseIdUpdate(HouseList houselist) {
        HouseList list = houseListMapper.findhouseidupdate(houselist);
        return list;
    }

    @Override
    public void updateHouse(HouseList houseList) {
        houseListMapper.updatehouse(houseList);

    }

    @Override
    public HouseList findId(int id) {
        HouseList list = houseListMapper.findid(id);
        return list;
    }

    @Override
    public void updateHouseStatus(HouseList houseList) {
        // TODO Auto-generated method stub
        houseListMapper.updatehousestatus(houseList);
    }

    @Override
    public void deleteHouseByHouseId(String houseId) {
        houseListMapper.deletehousebyhouseid(houseId);

    }


}
