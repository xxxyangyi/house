package dao;

import Pojo.ApplyOut;

import java.util.List;

public interface ApplyoutMapper {
    public void insertapplyout(ApplyOut applyout);

    List<ApplyOut> findallapplyout();

    public void updateapplyout(ApplyOut applyout);

    public void updateapplyoutbyhouse(ApplyOut applyout);

    public ApplyOut findbyid(Integer id);

    public void deleteapplyout(Integer id);
}
