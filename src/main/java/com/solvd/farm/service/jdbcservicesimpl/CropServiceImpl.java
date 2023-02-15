package com.solvd.farm.service.jdbcservicesimpl;

import com.solvd.farm.DAO.CropDAO;
import com.solvd.farm.DAO.jdbcimpl.CropDAOImpl;
import com.solvd.farm.binary.Crop;
import com.solvd.farm.service.CropService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Random;

public class CropServiceImpl implements CropService {
    private static final Logger LOGGER = LogManager.getLogger(CropServiceImpl.class);
    public Crop growCrop() {
        String[] names = {"Corn", "Wheat", "Rice", "Barley", "Soybeans", "Pumpkin", "Oats"};
        String[] types = {"Grain", "Oilseed", "Pulse"};
        String[] growthStages = {"Spring", "Summer", "Fall", "Winter"};

        Random random = new Random();
        int randomIndex = random.nextInt(names.length);
        String randomName = names[randomIndex];

        randomIndex = random.nextInt(types.length);
        String randomType = types[randomIndex];

        randomIndex = random.nextInt(growthStages.length);
        String randomGrowthStage = growthStages[randomIndex];

        Crop crop = new Crop(1, randomName, randomType, randomGrowthStage);
        CropDAO cropDAO = new CropDAOImpl();
        cropDAO.create(crop);

        LOGGER.info("Crop created");
        return crop;
    }

    @Override
    public void viewAllCrops() {
        CropDAOImpl cropDAOImpl = new CropDAOImpl();
        List<Crop> crops = cropDAOImpl.getAll();
        for (Crop crop : crops) {
            LOGGER.info("Crop ID: " + crop.getId());
            LOGGER.info("Crop Name: " + crop.getName());
            LOGGER.info("Crop Variety: " + crop.getVariety());
            LOGGER.info("Crop Growing Season: " + crop.getGrowingSeason());
        }
    }

    @Override
    public boolean harvestCropById(long id) {
        CropDAO cropDAO = new CropDAOImpl();
        Crop crop = cropDAO.getById(id);
        if (crop != null) {
            return cropDAO.delete(crop);
        }
        return false;
    }


}
