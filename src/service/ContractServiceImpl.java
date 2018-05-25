package service;

import Pojo.Contract;
import dao.ContractMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ContractServiceImpl implements ContractService {
    @Autowired
    private ContractMapper hetongMapper;

    @Override
    public void insertContract(Contract contract) {
        hetongMapper.insertContract(contract);

    }

    @Override
    public Contract findContract(String houseId) {
        Contract contract = hetongMapper.findContract(houseId);
        return contract;
    }

    @Override
    public void updateContract(Contract contract) {
        hetongMapper.updateContract(contract);
    }

    @Override
    public void deleteContract(String house_id) {
        // TODO Auto-generated method stub
        hetongMapper.deleteContract(house_id);

    }

}
