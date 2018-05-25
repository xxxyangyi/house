package service;

import Pojo.ApplyOut;
import Pojo.ZuList;

import java.util.List;

public interface ApplyOutService {
    void insertApplyOut(ZuList zulist);

    List<ApplyOut> findAllApplyOut();

    void updateApplyOut(ApplyOut applyout);

    void agreeApplyOut(Integer id);

    void deleteApplyOut(Integer id);
}
