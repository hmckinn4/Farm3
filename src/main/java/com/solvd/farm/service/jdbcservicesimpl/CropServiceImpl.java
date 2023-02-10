package com.solvd.farm.service.jdbcservicesimpl;

import com.solvd.farm.DAO.CropDAO;
import com.solvd.farm.DAO.jdbcimpl.CropDAOImpl;
import com.solvd.farm.binary.Crop;
import com.solvd.farm.service.CropService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Random;

public class CropServiceImpl implements CropService {
    private static final Logger logger = LogManager.getLogger(CropServiceImpl.class);
    public Crop growCrop() {
        String[] names = {"Corn", "Wheat", "Rice", "Barley", "Soybeans"};
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

        logger.info("Crop created");
        return crop;
    }

}
