package com.solvd.farm.DAO;

import com.solvd.farm.binary.Farm;

import java.util.List;

public interface FarmDAO extends AbstractDAO<Farm> {
    public List<Farm> getAllCrops();

    public int updateCrop(Farm farm);
    public int deleteCrop(int id);
}
