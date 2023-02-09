package com.solvd.farm.service.jdbcservicesimpl;

import com.solvd.farm.DAO.CropDAO;
import com.solvd.farm.DAO.jdbcimpl.CropDAOImpl;
import com.solvd.farm.binary.Crop;
import com.solvd.farm.service.CropService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CropServiceImpl implements CropService {
    private static final Logger logger = LogManager.getLogger(CropServiceImpl.class);
    public Crop growCrop() {
        Crop crop = new Crop(1, "Corn", "Grain", "Spring");
        // set crop properties, such as name, type, and growth stage

        CropDAO cropDAO = new CropDAOImpl();
        cropDAO.create(crop);

        logger.info("Crop created");
        return crop;
    }

}
