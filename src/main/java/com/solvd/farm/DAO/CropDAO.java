package com.solvd.farm.DAO;

import com.solvd.farm.binary.Crop;

import java.util.List;

public interface CropDAO extends AbstractDAO<Crop> {
    public List<Crop> getAllCrops();

    public int updateCrop(Crop crop);
    public int deleteCrop(int id);
}
