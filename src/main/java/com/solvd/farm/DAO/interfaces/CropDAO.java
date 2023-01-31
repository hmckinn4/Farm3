package com.solvd.farm.DAO.interfaces;

import com.solvd.farm.binary.representations.Crop;

import java.util.List;

public interface CropDAO {
    public List<Crop> getAllCrops();
    public Crop getCropById(int id);
    public int addCrop(Crop crop);
    public int updateCrop(Crop crop);
    public int deleteCrop(int id);
}
