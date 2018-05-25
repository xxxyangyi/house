package dao;

import Pojo.ApplyOut;
import java.util.List;

public interface ApplyOutMapper {
    void insertApplyOut(ApplyOut applyout);

    List<ApplyOut> findAllApplyOut();

    void updateApplyOut(ApplyOut applyout);

    void updateApplyOutByHouse(ApplyOut applyout);

    ApplyOut findById(Integer id);

    void deleteApplyOut(Integer id);
}
