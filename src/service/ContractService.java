package service;

import Pojo.Contract;

public interface ContractService {

    void insertContract(Contract contract);

    Contract findContract(String houseId);

    void updateContract(Contract contract);

    void deleteContract(String houseId);
}
