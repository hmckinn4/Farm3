package com.solvd.farm.service;

import com.solvd.farm.binary.Crop;

public interface CropService {
    Crop growCrop();

    void viewAllCrops();

    boolean harvestCropById(long id);
}
