package dao;

import Pojo.Contract;

public interface ContractMapper {

    void insertContract(Contract contract);

    Contract findContract(String houseId);

    void updateContract(Contract contract);

    void deleteContract(String houseId);
}
